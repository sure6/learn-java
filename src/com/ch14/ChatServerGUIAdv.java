package com.ch14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerGUIAdv {
    private JFrame frame;
    private JTextArea logArea;
    private JList<String> userList;           // 新增：用户列表显示组件
    private DefaultListModel<String> listModel; // 新增：列表数据模型
    private JButton startButton;
    private JButton stopButton;
    private ServerSocket serverSocket;
    private Set<ClientHandler> clientHandlers = Collections.synchronizedSet(new HashSet<>());
    private boolean isRunning = false;

    public static void main(String[] args) {
        new ChatServerGUIAdv().initialize();
    }

    void initialize() {
        // 创建主窗口
        frame = new JFrame("聊天服务器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLayout(new BorderLayout());

        // 中间分割面板：左侧日志，右侧用户列表
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(500);

        // 日志区域（左侧）
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder("服务器日志"));
        splitPane.setLeftComponent(logScrollPane);

        // 用户列表区域（右侧）- 新增
        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane userScrollPane = new JScrollPane(userList);
        userScrollPane.setBorder(BorderFactory.createTitledBorder("在线用户列表"));
        splitPane.setRightComponent(userScrollPane);

        frame.add(splitPane, BorderLayout.CENTER);

        // 控制面板
        JPanel controlPanel = new JPanel();
        startButton = new JButton("启动服务器");
        stopButton = new JButton("停止服务器");
        stopButton.setEnabled(false);
        // 启动服务器按钮监听器
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });
        // 停止服务器按钮监听器
        stopButton.addActionListener(e -> stopServer());

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    // 启动服务器方法
    private void startServer() {
        int port = 12345;
        try {
            serverSocket = new ServerSocket(port);
            isRunning = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            logMessage("服务器已启动，监听端口: " + port);

            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        while (isRunning) {
                            Socket clientSocket = serverSocket.accept();
                            // 创建客户端处理线程并启动
                            ClientHandler clientHandler = new ClientHandler(clientSocket);
                            clientHandlers.add(clientHandler);
                            new Thread(clientHandler).start();
                        }
                    } catch (IOException e) {
                        if (isRunning) {
                            logMessage("服务器异常: " + e.getMessage());
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            logMessage("无法启动服务器: " + e.getMessage());
        }
    }

    // 停止服务器方法
    private void stopServer() {
        isRunning = false;
        try {
            for (ClientHandler client : clientHandlers) {
                client.close();
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            logMessage("服务器已停止");
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        } catch (IOException e) {
            logMessage("停止服务器时出错: " + e.getMessage());
        }
    }

    // 日志记录方法
    private void logMessage(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    // 添加用户到列表
    private void addUserToList(String userName) {
        SwingUtilities.invokeLater(() -> {
            listModel.addElement(userName);
        });
    }

    // 从列表中移除用户
    private void removeUserFromList(String userName) {
        SwingUtilities.invokeLater(() -> {
            listModel.removeElement(userName);
        });
    }

    // 客户端处理类
    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                logMessage("新客户端连接: " + clientSocket.getInetAddress());
                // 创建输入输出流, 用于与客户端进行通信
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                // 创建输入流, 用于读取客户端发送的数据
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // 读取客户端名称, 作为用户标识
                clientName = in.readLine();
                logMessage(clientName + " 加入了聊天室");

                // 添加到用户列表, 用于显示在线用户
                addUserToList(clientName);

                broadcast(clientName + " 加入了聊天室", this);
                broadcastUserList();// 广播用户列表

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String message = "[" + clientName + "]: " + inputLine;
                    logMessage(message);
                    broadcast(message, this);
                }
            } catch (IOException e) {
                logMessage(clientName + " 异常断开: " + e.getMessage());
            } finally {
                try {
                    clientHandlers.remove(this);
                    if (clientName != null) {
                        logMessage(clientName + " 离开了聊天室");
                        // 新增：从用户列表中移除
                        removeUserFromList(clientName);
                        broadcast(clientName + " 离开了聊天室", this);
                        broadcastUserList();  // 新增：广播用户列表
                    }
                    close();
                } catch (Exception e) {
                    logMessage("清理客户端资源时出错: " + e.getMessage());
                }
            }
        }
        // 发送消息给客户端
        public void sendMessage(String message) {
            out.println(message);
        }

        // 关闭客户端连接
        public void close() throws IOException {
            if (out != null) out.close();
            if (in != null) in.close();
            if (clientSocket != null) clientSocket.close();
        }
    }

    // 广播消息给所有客户端，排除指定客户端
    private void broadcast(String message, ClientHandler excludeClient) {
        for (ClientHandler client : clientHandlers) {
            if (client != excludeClient) {
                client.sendMessage(message);
            }
            // 同时广播最新的用户列表
            broadcastUserList();
        }
    }

    // 广播用户列表给所有客户端
    private void broadcastUserList() {
        StringBuilder userListStr = new StringBuilder();
        for (ClientHandler client : clientHandlers) {
            if (userListStr.length() > 0) {
                userListStr.append(",");
            }
            userListStr.append(client.clientName);
        }
        String message = "[USER_LIST]" + userListStr.toString();
        for (ClientHandler client : clientHandlers) {
            client.sendMessage(message);
        }
    }
}

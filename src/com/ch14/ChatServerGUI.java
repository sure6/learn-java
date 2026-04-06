package com.ch14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerGUI {
    private JFrame frame;
    private JTextArea logArea;
    private JButton startButton;
    private JButton stopButton;
    private ServerSocket serverSocket;
    private Set<ClientHandler> clientHandlers = Collections.synchronizedSet(new HashSet<>());
    private boolean isRunning = false;

    public static void main(String[] args) {
        new ChatServerGUI().initialize();
    }

    private void initialize() {
        // 创建主窗口
        frame = new JFrame("聊天服务器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // 日志区域
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 控制面板
        JPanel controlPanel = new JPanel();
        startButton = new JButton("启动服务器");
        stopButton = new JButton("停止服务器");
        stopButton.setEnabled(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });
        stopButton.addActionListener(e -> stopServer());

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

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

    private void logMessage(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

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
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                clientName = in.readLine();
                logMessage(clientName + " 加入了聊天室");
                broadcast(clientName + " 加入了聊天室", this);

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
                        broadcast(clientName + " 离开了聊天室", this);
                    }
                    close();
                } catch (Exception e) {
                    logMessage("清理客户端资源时出错: " + e.getMessage());
                }
            }
        }


        public void sendMessage(String message) {
            out.println(message);
        }

        public void close() throws IOException {
            if (out != null) out.close();
            if (in != null) in.close();
            if (clientSocket != null) clientSocket.close();
        }
    }

    private void broadcast(String message, ClientHandler excludeClient) {
        for (ClientHandler client : clientHandlers) {
            if (client != excludeClient) {
                client.sendMessage(message);
            }
        }
    }
}
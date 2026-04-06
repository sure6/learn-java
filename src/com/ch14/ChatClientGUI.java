package com.ch14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClientGUI {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private JTextField nameField;
    private JButton connectButton;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String serverAddress = "localhost";
    private int port = 12345;
    private String clientName;

    public static void main(String[] args) {
        new ChatClientGUI().initialize();
    }

    public void initialize() {
        // 创建主窗口
        frame = new JFrame("聊天客户端");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // 聊天区域
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 输入面板
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputField.setEnabled(false);
        sendButton = new JButton("发送");
        sendButton.setEnabled(false);

        inputField.addActionListener(e -> sendMessage());
        sendButton.addActionListener(e -> sendMessage());

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // 连接面板
        JPanel connectPanel = new JPanel(new BorderLayout());
        nameField = new JTextField("请输入昵称");
        connectButton = new JButton("连接服务器");

        connectButton.addActionListener(e -> connectToServer());

        connectPanel.add(nameField, BorderLayout.CENTER);
        connectPanel.add(connectButton, BorderLayout.EAST);

        // 底部面板
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(connectPanel, BorderLayout.NORTH);
        bottomPanel.add(inputPanel, BorderLayout.SOUTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void connectToServer() {
        clientName = nameField.getText().trim();
        if (clientName.isEmpty()) {
            chatArea.append("请输入有效的昵称\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());

            return;
        }

        try {
            socket = new Socket(serverAddress, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 发送昵称
            out.println(clientName);

            // 启用输入
            inputField.setEnabled(true);
            sendButton.setEnabled(true);
            nameField.setEnabled(false);
            connectButton.setEnabled(false);

            chatArea.append("已连接到服务器，可以开始聊天了！\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());

            // 启动接收消息线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String message;
                        while ((message = in.readLine()) != null) {
                            chatArea.append(message+"\n");
                            chatArea.setCaretPosition(chatArea.getDocument().getLength());
                        }
                    } catch (IOException e) {
                        chatArea.append("与服务器的连接已断开\n");
                        chatArea.setCaretPosition(chatArea.getDocument().getLength());
                    } finally {
                        try {
                            if (socket != null) socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        inputField.setEnabled(false);
                        sendButton.setEnabled(false);
                        connectButton.setEnabled(true);
                    }
                }
            }).start();
        } catch (IOException e) {
            chatArea.append("连接服务器失败: " + e.getMessage() + "\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
        }
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            out.println(message);
            inputField.setText("");
        }
    }

}

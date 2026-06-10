package com.ch14;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatServerV2 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("【服务端v2】已启动，等待客户端连接...");

            Socket socket = serverSocket.accept();
            System.out.println("客户端已连接！进入回合制聊天（请交替发言）...");

            // 获取输入流（听）和输出流（说）
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // 1. 先接收客户端的消息（此处会阻塞，直到客户端发来数据）
                System.out.println("【等待对方发言...】");
                String clientMsg = reader.readLine();
                if (clientMsg == null || clientMsg.equalsIgnoreCase("bye")) {
                    System.out.println("客户端断开连接。");
                    break;
                }
                System.out.println("[客户端说]: " + clientMsg);

                // 2. 收到后，自己才能回复（此处会因等待控制台输入而阻塞）
                System.out.print("[我回复]: ");
                String myMsg = scanner.nextLine();
                writer.println(myMsg);
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package com.ch14;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClientV2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            System.out.println("成功连接服务器！进入回合制聊天（请交替发言）...");

            // 获取输入流（听）和输出流（说）
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // 1. 客户端先说（此处会因等待控制台输入而阻塞）
                System.out.print("[我发言]: ");
                String myMsg = scanner.nextLine();
                writer.println(myMsg);
                if (myMsg.equalsIgnoreCase("bye")) {
                    break;
                }

                // 2. 说完后，等待服务端回复（此处会阻塞，直到服务端发来数据）
                System.out.println("【等待服务器回复...】");
                String serverMsg = reader.readLine();
                if (serverMsg == null) {
                    System.out.println("服务器已断开。");
                    break;
                }
                System.out.println("[服务器说]: " + serverMsg);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


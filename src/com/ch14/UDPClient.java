package com.ch14;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        // 服务器地址和端口
        final String serverHost = "localhost";
        final int serverPort = 9876;

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(serverHost);
            Scanner scanner = new Scanner(System.in);

            System.out.println("UDP客户端已启动，输入消息发送到服务器(输入'exit'退出):");

            while (true) {
                // 读取用户输入
                System.out.print("> ");
                String message = scanner.nextLine();

                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                // 发送数据
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendData,
                        sendData.length,
                        serverAddress,
                        serverPort
                );
                clientSocket.send(sendPacket);

                // 准备接收响应
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // 接收响应(设置超时时间，单位毫秒)
                clientSocket.setSoTimeout(5000);
                try {
                    clientSocket.receive(receivePacket);
                    String response = new String(
                            receivePacket.getData(),
                            0,
                            receivePacket.getLength()
                    );
                    System.out.println("服务器响应: " + response);
                } catch (SocketTimeoutException e) {
                    System.out.println("请求超时，未收到服务器响应");
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("未知主机: " + serverHost);
        } catch (IOException e) {
            System.err.println("客户端异常: " + e.getMessage());
        }
    }
}
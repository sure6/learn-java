package com.ch14;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        // 指定服务器端口
        final int serverPort = 9876;

        try (DatagramSocket serverSocket = new DatagramSocket(serverPort)) {
            System.out.println("UDP服务器已启动，监听端口: " + serverPort);

            byte[] receiveData = new byte[1024];

            while (true) {
                // 准备接收数据包
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // 接收数据
                serverSocket.receive(receivePacket);

                // 解析收到的数据
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("收到来自 " + clientAddress + ":" + clientPort + " 的消息: " + clientMessage);

                // 准备响应数据
                String responseMessage = "服务器已收到你的消息: " + clientMessage;
                byte[] sendData = responseMessage.getBytes();

                // 发送响应
                DatagramPacket sendPacket = new DatagramPacket(
                        sendData,
                        sendData.length,
                        clientAddress,
                        clientPort
                );
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            System.err.println("服务器异常: " + e.getMessage());
        }
    }
}

package com.ch14;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        String[] answer={"桂林的平均海拔约为150米","4个","南宁市"};
        ServerSocket serverSocket = null;
        Socket accept = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            serverSocket=new ServerSocket(2010);
        } catch (IOException e) {
            System.out.println(e);
        }


        try {
            System.out.println("等待客户呼叫");
            accept = serverSocket.accept();
            System.out.println("客户的地址："+accept.getInetAddress());
            System.out.println("客户的端口："+accept.getPort());
            out=new DataOutputStream(accept.getOutputStream());
            in=new DataInputStream(accept.getInputStream());
            for(int i=0;i<answer.length;i++){
                String s = in.readUTF();
                System.out.println("服务器收到客户的提问："+s);
                out.writeUTF(answer[i]);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("客户已断开"+e);
        }
    }
}

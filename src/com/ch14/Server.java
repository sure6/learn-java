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
            // 服务器不许指定ip 只需要指定端口，因为服务运行时候根据自己所运行的主机获取本地ip
            serverSocket=new ServerSocket(2010);
        } catch (IOException e) {
            System.out.println(e);
        }


        try {
            System.out.println("等待客户呼叫");
            //  等待客户呼叫  accept 是一个堵塞方法 客户端连接上服务端才会返回accept对象
            accept = serverSocket.accept();
            System.out.println("客户的地址："+accept.getInetAddress());
            System.out.println("客户的端口："+accept.getPort());
            //  获取输入输出流 从客户端读取 写给客户端
            out=new DataOutputStream(accept.getOutputStream());
            //  获取输入输出流 向客户端写入 从客户端读取
            in=new DataInputStream(accept.getInputStream());
            for(int i=0;i<answer.length;i++){
                //  读取客户提问 从客户端读取
                String s = in.readUTF();
                //  显示客户提问
                System.out.println("服务器收到客户的提问："+s);
                //  回复客户 写给客户端
                out.writeUTF(answer[i]);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("客户已断开"+e);
        }
    }
}

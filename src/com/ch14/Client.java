package com.ch14;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String[] mess={"珠穆朗玛峰的高度是多少？","桂林有多少个5A级景区？","广西壮族自治区首府是哪个城市"};
        Socket socket=null;
        DataInputStream in=null;
        DataOutputStream out=null;
        try {
            socket = new Socket("127.0.0.1", 2010);
            //  从服务器获取输入输出流 读取服务器回答
            in = new DataInputStream(socket.getInputStream());
            //  向服务器获取输入输出流 向服务器写入
            out = new DataOutputStream(socket.getOutputStream());
            //  遍历问题数组
            for(int i=0;i<mess.length;i++){
                //  向服务器写入问题
                out.writeUTF(mess[i]);
                //  读取服务器回答
                String s = in.readUTF();
                //  显示服务器回答
                System.out.println("客户收到服务器的回答:"+s);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("服务器已断开"+e);
        }
    }
}

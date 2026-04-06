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
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            for(int i=0;i<mess.length;i++){
                out.writeUTF(mess[i]);
                String s = in.readUTF();
                System.out.println("客户收到服务器的回答:"+s);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("服务器已断开"+e);
        }
    }
}

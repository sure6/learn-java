package com.ch11;

import java.io.RandomAccessFile;

public class Example10_9 {
    public static void main(String[] args) {
        RandomAccessFile in=null;
        try {
            in=new RandomAccessFile("C:\\Users\\dell\\week15\\src\\ch12\\Example10_8.java","rw");
            long length=in.length();
            long position=0;
            in.seek(position);// 游标从0开始
            while(position<length){
                String str=in.readLine();
                byte[] bytes=str.getBytes("iso-8859-1");
                str=new String(bytes);
                position=in.getFilePointer();// 当前光标所在位置
                System.out.println(str);
            }
            in.close();
        }catch (Exception e){}
    }
}

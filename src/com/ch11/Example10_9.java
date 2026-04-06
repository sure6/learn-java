package com.ch11;

import java.io.RandomAccessFile;

public class Example10_9 {
    public static void main(String[] args) {
        RandomAccessFile in=null;
        try {
            in=new RandomAccessFile("D:\\idea-workspaces\\learn-java\\src\\com\\ch11\\Example10_8.java","rw");
            long length=in.length();
            long position=0;
            in.seek(position);
            while(position<length){
                String str=in.readLine();
                byte[] bytes=str.getBytes("iso-8859-1");
                str=new String(bytes);
                position=in.getFilePointer();
                System.out.println(str);
            }
            in.close();
        }catch (Exception e){}
    }
}

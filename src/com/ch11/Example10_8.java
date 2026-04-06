package com.ch11;

import java.io.RandomAccessFile;

public class Example10_8 {
    public static void main(String[] args) {
        RandomAccessFile raf=null;
        int data[]={1,2,3,4,5,6,7,8,9,10};
        try {
            raf=new RandomAccessFile("tom.dat","rw");
            for (int i=0;i<data.length;i++){
                raf.writeInt(data[i]);
            }
            for(long i=data.length-1;i>=0;i--){ // 一个int型整数占4个字节，raf从文件的第36字节读取最后一个整数
                raf.seek(i*4);
                System.out.printf("\t%d",raf.readInt());//每隔4个字节往前读取一个整数
            }
            raf.close();
        }catch (Exception e){}
    }
}

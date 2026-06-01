package com.ch11;

import java.io.*;

public class Example10_5 {
    public static void main(String[] args) {
        byte[] a="新年快乐".getBytes();
        byte[] b="Happy New Year".getBytes();
        //1, 确定输出流的目的地 a.txt不存在的文件
        File file = new File("a.txt");
        OutputStream out;
        try {
            // 2 创建指向目的地的输出流对象 对象向上转型
//            OutputStream out = new FileOutputStream(file);
//            System.out.println(file.getName()+"的大小："+file.length()+"字节");
//            // 3 让输出流把数据写入到目的地
//            out.write(a);
//            // 4. 关闭流
//            out.close();
            // 2 创建指向目的地的输出流对象 对象向上转型 追加形式写入
            out=new FileOutputStream(file);
            System.out.println(file.getName()+"的大小："+file.length()+"字节");
            //3 让输出流把数据写入到目的地
            out.write(b,0,b.length);
            System.out.println(file.getName()+"的大小："+file.length()+"字节");
            // 4. 关闭流  flush()数据先放入缓冲区，然后进行写入目的地
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

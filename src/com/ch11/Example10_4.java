package com.ch11;

import java.io.*;

public class Example10_4 {
    public static void main(String[] args) {
        int n=-1; //标志
        // 定义一个字节长度
        // 1024字节=1kb
        // 字符流 char[]
        byte[] bytes = new byte[1024];
        //1.确定输入流的源
        File file = new File("C:\\Users\\dell\\week15\\src\\ch12\\Example10_4.java");
        try {
            //2. 创建指向源的输入流对象 对象向上转型 字符流以reader方式
            InputStream in = new FileInputStream(file);
            //3. 输入流去读取源中的数据。 字节流读出的单位是字节，字符是按字符数量来读
            while((n=in.read(bytes,0,1024))!=-1){
                String s = new String(bytes, 0, n);
                System.out.println(s);
            }
            // 4. 关闭流
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

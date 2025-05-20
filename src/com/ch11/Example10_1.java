package com.ch11;

import java.io.File;
import java.io.IOException;

public class Example10_1 {
    public static void main(String[] args) {
        File f = new File("D:\\idea-workspaces\\learn-java\\abc.txt");
        System.out.println(f.getName()+" 的可读的吗  "+f.canRead());
        System.out.println(f.getName()+" 的长度 "+f.length());
        System.out.println(f.getName()+" 的绝对路径 "+f.getAbsolutePath());

        File f2 = new File("abcd.txt");
        if(!f2.exists()){
            try {
                f2.createNewFile();
                System.out.println("在当前目录(运行程序目录)下创建新文件："+f2.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

package com.ch11;

import java.io.File;
import java.io.IOException;

public class Example10_2{
    public static void main(String[] args)  {
        // 创建目录对象
        File fd = new File("java");
        System.out.println(fd.isDirectory());
        boolean boo = fd.mkdir(); // Linux mkdir  （make directory）
        if(boo){
            System.out.println("新建子目录 "+fd.getName());
        }

        File dir = new File(".");// Linux 通配符， 。代表当前目录，。。上层目录
        // 当前项目根目录下的所有文件和目录，只能访问当前项目下一级，如果要访问所有文件则需要用递归方式
        System.out.println("全部文件(包括文件夹): "+dir.getAbsolutePath());
        String[] files = dir.list();// 列出当前目录下的所有文件
        for(String file : files){
            System.out.println(file);
        }

        FileAccpet fileAccpet = new FileAccpet();
        fileAccpet.setExtendName("java");// 后缀为java
        System.out.println("仅仅列出java源文件：");
        File[] files1s = dir.listFiles(fileAccpet);
        if(files1s!=null){
            for(File file : files1s){
                System.out.println(file.getName());
            }
        }


        // 执行程序
        try {
            Runtime runtime = Runtime.getRuntime();
            // execute
            runtime.exec(new File("C:\\Users\\dell\\AppData\\Local\\Programs\\Microsoft VS Code\\Code.exe").getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

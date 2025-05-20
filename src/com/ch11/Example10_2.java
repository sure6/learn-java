package com.ch11;

import java.io.File;
import java.io.IOException;

public class Example10_2{
    public static void main(String[] args)  {
        File fd = new File("java");
        System.out.println(fd.isDirectory());
        boolean boo = fd.mkdir();
        if(boo){
            System.out.println("新建子目录 "+fd.getName());
        }

        File dir = new File(".");
        System.out.println("全部文件(包括文件夹): "+dir.getAbsolutePath());
        String[] files = dir.list();
        for(String file : files){
            System.out.println(file);
        }
        FileAccpet fileAccpet = new FileAccpet();
        fileAccpet.setExtendName("java");
        System.out.println("仅仅列出java源文件：");
        File[] files1s = dir.listFiles(fileAccpet);
        for(File file : files1s){
            System.out.println(file.getName());
        }

        // 执行程序
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(new File("D:\\Microsoft VS Code\\Code.exe").getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

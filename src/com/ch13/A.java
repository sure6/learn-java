package com.ch13;

import java.io.File;

public class A {
    public static void main(String[] args) {
        File f=new File("a.txt");
        System.out.println(f.getName()+"的长度为"+f.length()+"字节"); // 【代码1】
    }
}


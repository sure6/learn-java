package com.ch05.example2;

public class Example5_2 {
    public static void main(String[] args) {
        ChinaPeople zhangsan = new ChinaPeople();
        System.out.println("子类对象未继承的averHeight的值是："+zhangsan.getAverHeight());
        zhangsan.setHeight(178);
        System.out.println("子类对象的实例变量height的值是："+zhangsan.getHeight());
    }
}

package com.ch13;

public class B{
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run(){
                String name=Thread.currentThread().getName();
                System.out.println(name); // 【代码2】
                System.out.println(Thread.currentThread().getPriority()); // 【代码3】
                System.out.println("thread1 is running"); // 【代码4】
            }

        });
        t.setName("线程1");
        t.start();
    }
}




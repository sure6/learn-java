package com.ch04;

public class Singleton {

    private static Singleton demo;
    private Singleton(){

    }

    public static Singleton getInstance(){
        if(demo == null){
            demo = new Singleton();
        }

        return demo;
    }

}


class  Entry{

    public static void main(String[] args) {
        Singleton demo = Singleton.getInstance();
        Singleton demo2 = Singleton.getInstance();
        System.out.println(demo);
        System.out.println(demo2);
        System.out.println(demo==demo2);
    }
}
package com.leesure.controllers;

public class Test {

    public static String str="static";

    Test(){
        System.out.println("constructor");
    }
    {
        final int a=1;
        System.out.println("default code block ");
    }
    static {
        System.out.println("static code block ");
    }

    public void helloWorld(int... nums){
        String x="x";
        int z=1;
        System.out.println(str);
        class a{
            int b=1;

            public void abc(){
                System.out.println(z);
            }
        }
    }
    public static void main(String[] args) {
        new Test(){{
            String a="a";
            String b="b";
            System.out.println(a+b);
        }};
    }
}

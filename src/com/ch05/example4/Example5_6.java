package com.ch05.example4;

class A{
    Object get(){
        return null;
    }
}

class B extends A{
    @Override
    Integer get(){
        return Integer.valueOf(10);
    }
}

public class Example5_6 {
    public static void main(String[] args) {
        B b=new B();
        Integer t=b.get();
        System.out.println(t.intValue());
    }
}

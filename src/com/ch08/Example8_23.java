package com.ch08;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Rect{
    double width,height,area;
    public Rect(){

    }
    public Rect(double w, double h){
        width = w;
        height = h;
    }
    public double getArea(){
        area = width * height;
        return area;
    }
}

public class Example8_23 {
    public static void main(String[] args) {
        Rect rect=new Rect();
        Class<? extends Rect> cs = rect.getClass();
        System.out.println(cs==Rect.class);
        String className = cs.getName();
        Constructor<?>[] con = cs.getDeclaredConstructors();
        Field[] fields = cs.getDeclaredFields();
        Method[] methods = cs.getDeclaredMethods();
        System.out.println("类的名字："+className);
        System.out.println("类中有如下构造方法：");
        for(Constructor c:con){
            System.out.println(c.toString());
        }
        System.out.println("类中有如下变量：");
        for(Field f:fields){
            System.out.println(f.toString());
        }
        System.out.println("类中有如下方法：");
        for(Method m:methods){
            System.out.println(m.toString());
        }

    }
}

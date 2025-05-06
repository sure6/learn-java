package com.ch08;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Example8_24 {
    public static void main(String[] args) {
        try {
            Class<?> cs=Class.forName("com.ch08.Circle");
            Constructor<?> constructor = cs.getDeclaredConstructor();
            Circle c = (Circle)constructor.newInstance();
            c.setRadius(100);
            double area = c.getArea();
            String format = String.format("%10.2f", area);
            Method radius = cs.getDeclaredMethod("getRadius");
            System.out.println("circle的半径："+radius.invoke(c));
            System.out.println("circle的面积，保留2位有效数字："+format);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

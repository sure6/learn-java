package com.ch07;

// 演示静态嵌套类的Java程序

// 导入所需的类
import java.util.*;

// Class 1
// 外部类：
class Outer3 {

    // 方法
    private static void outerMethod() {

        // 打印
        System.out.println("inside outerMethod");
    }

    // Class 2
    // 静态内部类
    static class Inner {

        public static void display() {

            // 打印
            System.out.println("inside inner class Method");

            // 调用main（）方法中的方法
            outerMethod();
        }
    }
}

// Class 3
// Main class
class GFG2 {

    // 入口
    public static void main(String args[]) {

        // 静态显示方法，而不是该类的实例。
        Outer3.Inner.display();
    }
}

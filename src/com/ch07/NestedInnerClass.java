package com.ch07;

// 演示嵌套类的Java程序

// Class 1
// 辅助类
class Outer {


    // 方法在外部类中定义
    void outerMethod(){

        // 打印语句
        System.out.println("inside outerMethod");
    }
    // Class 2
    // 简单嵌套内部类
    class Inner {

        // 内部类的Show（）方法
        public void show() {
            // 打印语句
            System.out.println("In a nested class method");
        }
    }
}

// Class 2
// 入口类
class Main {

    // 入口方法
    public static void main(String[] args) {

        // 注意内部类对象是如何在内部创建的
        Outer.Inner in = new Outer().new Inner();

        // 在创建的对象上面调用show（）方法
        in.show();
    }
}

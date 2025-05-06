package com.ch07;

// Java程序说明内部类可以
// 在外部类的方法中声明

// Class 1
// 外部类
class Outer2 {

    // 外部类中的方法
    void outerMethod() {
        // 注意：局部内部类在JDK 1.7之前不能访问非final的局部变量。从JDK 1.8开始，可以在method local内部类中访问非final的局部变量。
        int x = 98;
        // 打印
        System.out.println("inside outerMethod");

        // Class 2
        // 内部类
        // 它是outerMethod（）的局部变量
        class Inner {

            // 方法在内部类中定义
            void innerMethod() {

                // 当内部类存在时打印语句
                System.out.println("inside innerMethod"+x);
            }
        }

        // 创建内部类的对象
        Inner y = new Inner();

        // 调用内部定义的方法
        y.innerMethod();
    }
}

// Class 3
// 主类
class GFG {

    // 入口
    public static void main(String[] args) {

        // 在main（）中创建外部类的对象
        Outer2 x = new Outer2();

        // 调用相同的方法
        // 就像我们上面对内部类所做的那样
        x.outerMethod();
    }
}

package com.ch07;

// 匿名内部类的Java程序
// 无名称声明
// 作为指定类型的子类

// 导入所需的类
import java.util.*;

// Class 1： 辅助类
class Demo {

    // 辅助类的方法
    void show() {
        System.out.println("我在super类的show method中");
    }
}
// 另外用的最多的是这种写法
//interface Demo{
//    void show();
//}

// Class 2：主类
class Flavor1Demo {

    //  一个以Demo为基类的匿名类
    static Demo d = new Demo() {
        // Method 1：  show() method
        @Override
        void show() {
            // 通过super关键字调用方法show（）
            super.show();

            System.out.println("我在Flavor1Demo类");
        }
    };

    // Method 2：入口
    public static void main(String[] args) {
        //在main（）方法中调用show（）方法
        d.show();
    }
}


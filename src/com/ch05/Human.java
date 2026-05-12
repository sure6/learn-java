package com.ch05;

public abstract class Human {
    // 1.抽象类不能new, 也就是实例化
    // 2.抽象类能声明成员变量
    int age=10;
    // 3.抽象类里面可以有抽象方法
    // 4. 类里面有抽象方法，这个类一定是抽象类， 类必须加abstract. 反之，不一定
    abstract int min(int x,int y);
    int max(int x, int y){
        return x>y?x:y;
    }

    // 开闭原则： 对扩展开放，对修改关闭
}

package com.ch05;

// 1 修饰类 类不能被继承
public class People {
    int age,leg=2,hand=2;
    // 3. 修饰基本类型，它的值不能修改
    final int a=1;
    // 4. 修饰引用数据类型
     static final int[] b={1,2,3,4};
    //2、 修饰方法不能被重写
    protected final Student showPeopleMess(){
        System.out.printf("%d 岁, %d只脚, %d只手\t",age,leg,hand);
        return null;
    }
}

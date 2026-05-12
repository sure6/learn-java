package com.ch05;

public class Student extends People {
    int number;
    void tellNumber(){
        System.out.printf("学号： %d\t",number);
    }
    int add(int x, int y){
        return x+y;
    }

//    @Override
//    public UniverStudent showPeopleMess(){
//        System.out.printf("student类：%d 岁, %d只脚, %d只手\t",age,leg,hand);
//        return null;
//    }
}

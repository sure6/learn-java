package com.ch06;
public interface Com {
    int MAX=100;
    int MAX2=200;
    int a = 0,b=1;
    void add();
    float sum(float a, float b);

    default int max(int a, int b) {
        return max2(a,b);
    }

    static void f(){
        max2(a,b);
        System.out.println("注意是从Java SE8开始的");
    }

    private static int max2(int x,int y){
        return x>y?x:y;
    }

}


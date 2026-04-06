package com.ch15;

import java.util.Stack;

public class Example15_6 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(1);
        int k=1;
        while(k<=10){
            for (int i=1;i<=2;i++){
                int f1 = stack.pop();
                int f2 = stack.pop();
                int temp=f1+f2;
                System.out.println(temp);
                stack.push(temp);
                stack.push(f2);
                k++;
            }
        }
    }
}

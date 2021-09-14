package com.leesure;

import java.util.Scanner;

public class Demo1 {
    public static int calcStr(String str){
        if (str.length()>5000) {
            return -1;
        }else {
            String[] s = str.split("");
            for (String s1:s) {
                System.out.println(s1);
            }
            return s[s.length-1].length();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calcStr("hello nowcoder"));
    }
}

package com.ch08;

import java.util.Arrays;

class SortString{
    public static void sort(String a[]){
        int count=0;
        // i0= melon j0=apple
        for(int i=0;i<a.length-1;i++){
            for (int j=i+1;j<a.length;j++){
                if(a[j].compareTo(a[i])<0){
                    // 交换
                    String temp=a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }
            }
        }
    }
}

public class Example8_3 {
    public static void main(String[] args) {
        String[] a={"aelon","apple","aear","aanana"};
        String[] b={"西瓜","苹果","梨","香蕉"};
        System.out.println("使用SortString类的方法按字典序排列数组a：");

        SortString.sort(a);
        for(String s: a){
            System.out.print("  "+s);
        }
        System.out.println("");
        System.out.println("使用类库中的Arrays类，按字典序排列数组b:");
        Arrays.sort(b);
        for (String s: b){
            System.out.print("  "+s);
        }
    }
}

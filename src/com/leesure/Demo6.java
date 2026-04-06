package com.leesure;

import java.util.*;
class E {
    public static void main(String args[]) {
        LinkedList< Integer> list=new LinkedList< Integer>();
        for(int i=1;i<=10;i++) {
            list.add(i);
        }
        System.out.println("翻转前，链表中的数据");
        Iterator<Integer> iter=list.iterator();
        while(iter.hasNext()){
            int n=iter.next();
            System.out.printf(" %d ,",n); //【代码9】
        }
        System.out.printf("\n");
        Collections.reverse(list);
        System.out.println("翻转后，链表中的数据");
        iter=list.iterator();
        while(iter.hasNext()){
            int n=iter.next();
            System.out.printf(" %d ,",n); //【代码10】
        }
        System.out.printf("\n");
    }
}





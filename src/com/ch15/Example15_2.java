package com.ch15;

import java.util.LinkedList;
import java.util.List;

public class Example15_2{
    public static void main(String[] args) {
        List<People> list = new LinkedList<>();
        People tom = new People(78);
        list.add(tom);
        list.add(new People(58));
        list.add(new People(68));
        list.add(new People(38));
        People item = list.get(3);
        System.out.println(item.height);
        System.out.println(list.indexOf(tom));
        System.out.println(list.contains(new People(78)));

    }
}

class People{
    public int height;
    People(int m){
        height=m;
    }
    public boolean equals(Object o){
        People p=(People)o;
        return height==p.height;
    }
}
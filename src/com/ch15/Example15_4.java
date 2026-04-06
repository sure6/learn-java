package com.ch15;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Example15_4 {
    public static void main(String[] args) {
        List<Student> list = new LinkedList<>();
        list.add(new Student("张三",188,86));
        list.add(new Student("李四",178,83));
        list.add(new Student("赵大龙",198,89));
        list.add(new Student("李云龙",175,80));
        System.out.println("排序前，链表中的数据：");
        Iterator<Student> iter = list.iterator();
        while(iter.hasNext()) {
            Student stu = iter.next();
            System.out.println(stu.name+"身高："+stu.height);
        }
        Collections.sort(list);
        System.out.println("排序（按身高）后，链表中的数据：");
        iter=list.iterator();
        while(iter.hasNext()) {
            Student stu = iter.next();
            System.out.println(stu.name+"身高"+stu.height);
        }
        Student zhaoLin = new Student("zhao xiao lin", 178, 80);
        int index = Collections.binarySearch(list, zhaoLin, null);
        if (index>=0){
            System.out.println(zhaoLin.name+"和链表中"+list.get(index).name+"身高相同.");
        }
        Collections.sort(list,(a,b)->{return a.weight-b.weight;});
        System.out.println("排序（按体重）后，链表中的数据：");
        iter=list.iterator();
        while(iter.hasNext()) {
            Student stu = iter.next();
            System.out.println(stu.name+"体重："+stu.weight);
        }

        index=Collections.binarySearch(list,zhaoLin,(a,b)->{return a.weight-b.weight;});

        if(index>=0){
            System.out.println(zhaoLin.name+"和链表中"+list.get(index).name+"体重相同");
        }
    }
}

class Student implements Comparable<Student> {
    int height=0;
    int weight;
    String name;
    Student(String n, int h, int w ) {
        name = n;
        height = h;
        weight = w;
    }
    public int compareTo(Student s) {
        return this.weight - s.weight;
    }
}
package com.ch11;

import java.io.Serializable;

// 必须实现Serializable标记接口
public class Student implements Serializable {
    private String name;
    private int age;
    // transient 只能用在属性上 隐藏该字段序列化
    private transient String password;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public  void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}


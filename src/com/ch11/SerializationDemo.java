package com.ch11;

import java.io.*;

/**
 * 序列化和反序列化
 */
public class SerializationDemo {
    public static void main(String[] args) {
        Student student = new Student("张三", 20);

        // 序列化（对象 → 文件）
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("student.dat"))) {
            oos.writeObject(student);
            System.out.println("对象已序列化");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 反序列化（文件 → 对象）
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("student.dat"))) {
            Student restored = (Student) ois.readObject();
            System.out.println("反序列化结果: " + restored);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}



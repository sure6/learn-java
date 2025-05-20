package com.ch10.model;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private List<Student> students;
    private static StudentDAO instance;

    private StudentDAO() {
        students = new ArrayList<>();
        // 初始化一些测试数据
        students.add(new Student("1001", "张三", 20, "男", "计算机科学"));
        students.add(new Student("1002", "李四", 21, "女", "软件工程"));
        students.add(new Student("1003", "王五", 22, "男", "信息技术"));
    }

    public static StudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    // 增
    public void addStudent(Student student) {
        students.add(student);
    }

    // 删
    public boolean deleteStudent(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                students.remove(s);
                return true;
            }
        }
        return false;
    }

    // 改
    public boolean updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    // 查
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}

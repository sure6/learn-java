package com.demo.model;

import com.demo.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private List<Student> students;
    private static StudentDAO instance;

    private StudentDAO() {
        students = new ArrayList<>();

//        students.add(new Student("1001", "张三", 20, "男", "计算机科学"));
//        students.add(new Student("1002", "李四", 21, "女", "软件工程"));
//        students.add(new Student("1003", "王五", 22, "男", "信息技术"));
        // 初始化一些测试数据
        try {
            Connection connection = DatabaseConnection.getInstance();
            Statement sql = connection.createStatement();
            ResultSet resultSet = sql.executeQuery("select * from students");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String major = resultSet.getString("major");
                students.add(new Student(id, name, age, gender, major));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // 单例模式
    public static StudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    // 增
    public void addStudent(Student student) {
//        students.add(student);
        Connection con = null;
        try {
            con = DatabaseConnection.getInstance();
            PreparedStatement prepareStatement = con.prepareStatement("insert into students values (?,?,?,?,?)");
            prepareStatement.setString(1, student.getId());
            prepareStatement.setString(2, student.getName());
            prepareStatement.setInt(3, student.getAge());
            prepareStatement.setString(4, student.getGender());
            prepareStatement.setString(5, student.getMajor());
            int ok = prepareStatement.executeUpdate();
            if (ok > 0) {
                System.out.println("添加成功");
                students.add(student);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // 删
    public boolean deleteStudent(String id) {
//        for (Student s : students) {
//            if (s.getId().equals(id)) {
//                students.remove(s);
//                return true;
//            }
//        }
        Connection con = null;
        try{
            con = DatabaseConnection.getInstance();
            PreparedStatement prepareStatement = con.prepareStatement("delete from students where id = ?");
            prepareStatement.setString(1, id);
            int ok = prepareStatement.executeUpdate();
            if (ok > 0) {
                System.out.println("删除成功");
                students.removeIf(s -> s.getId().equals(id));
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 改
    public boolean updateStudent(Student student) {
//
        Connection con = null;
        try{
            con = DatabaseConnection.getInstance();
            PreparedStatement prepareStatement = con.prepareStatement("update students set name = ?, age = ?, gender = ?, major = ? where id = ?");
            prepareStatement.setString(1, student.getName());
            prepareStatement.setInt(2, student.getAge());
            prepareStatement.setString(3, student.getGender());
            prepareStatement.setString(4, student.getMajor());
            prepareStatement.setString(5, student.getId());
            int ok = prepareStatement.executeUpdate();
            if (ok > 0) {
                System.out.println("修改成功");
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId().equals(student.getId())) {
                        students.set(i, student);
                        break;
                    }
                }
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 查
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(String id) {
//        for (Student s : students) {
//            if (s.getId().equals(id)) {
//                return s;
//            }
//        }
        Connection connection=null;
        try {
            connection = DatabaseConnection.getInstance();
            PreparedStatement prepareStatement = connection.prepareStatement("select * from students where id = ?");
            prepareStatement.setString(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                String id1 = resultSet.getString("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String major = resultSet.getString("major");
                return  new Student(id1, name, age, gender, major);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Student> searchStudents(String searchText) {
        List<Student> searchResults = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().contains(searchText) || student.getId().contains(searchText)) {
                searchResults.add(student);
            }
        }
        return searchResults;
    }
}

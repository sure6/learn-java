package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 静态私有实例
    private static Connection connection;

    // 数据库连接信息
    private static final String url = "jdbc:mysql://172.21.17.111:3306/recsys?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
    private static final String username = "root";
    private static final String password = "123456";

    // 私有构造方法
    private DatabaseConnection() throws SQLException {

    }

    // 获取实例的静态方法
    public static Connection getInstance() throws SQLException {
        try {
            // 注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("数据库驱动未找到: " + ex.getMessage());
        }

        return connection;
    }

    // 关闭连接
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
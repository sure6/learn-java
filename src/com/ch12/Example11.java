package com.ch12;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Example11 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement sql = null;
        try {
            connection = DatabaseConnection.getInstance();
            sql = connection.createStatement();
            ResultSet resultSet = sql.executeQuery("select * from users");

            while (resultSet.next()) {
                String userID = resultSet.getString(1);
                String userName = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);
                String phone = resultSet.getString(5);

                System.out.println(userID + "\t" + userName + "\t" + password + "\t" + email + "\t" + phone);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                sql.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

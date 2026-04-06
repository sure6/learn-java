package com.ch12;

import java.sql.*;

public class Example12 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement sql = null;
        try {
            connection = DatabaseConnection.getInstance();
//            sql = connection.createStatement();
//            int i = sql.executeUpdate("insert into user_info(name,password) values ('zhangsan','12345')");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user_info(name,password) values (?,?)");
            preparedStatement.setString(1, "Chen");
            preparedStatement.setString(2, "123456");
            int i = preparedStatement.executeUpdate();

            System.out.println(i);
            if(i>0){
                ResultSet resultSet = sql.executeQuery("select * from user_info");

                while (resultSet.next()) {
                    String userID = resultSet.getString(1);
                    String userName = resultSet.getString(2);
                    String password = resultSet.getString(3);


                    System.out.println(userID + "\t" + userName + "\t" + password);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
//                sql.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

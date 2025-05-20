package com.ch11;
/*
案例1：Java文件字符流实现自动登录功能案例

 */
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AutoLoginSystem {
    // 用户数据存储文件
    private static final String USER_DATA_FILE = "userdata.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("3. 退出");
            System.out.print("请选择操作: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("系统退出");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效选择，请重新输入");
            }
        }
    }

    // 注册功能
    private static void register(Scanner scanner) {
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();

        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            // 第二个参数默认false
            fw = new FileWriter(USER_DATA_FILE, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            // 将用户名和密码写入文件，格式为：username:password
            pw.println(username + ":" + password);

            // 在register方法中加密密码
//            String encryptedPwd = encryptPassword(password);
//            pw.println(username + ":" + encryptedPwd);
            System.out.println("注册成功！");

        } catch (IOException e) {
            System.out.println("注册失败: " + e.getMessage());
        }finally {
            if (pw!=null){
                pw.close();
            }
            if (bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    // 登录功能
    private static void login(Scanner scanner) {
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();

        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        FileReader fr = null;
        BufferedReader br = null;
        try {
            // FileReader没有提供读取一行的方法，因此用缓冲流能够按行读取
            fr = new FileReader(USER_DATA_FILE);
            br = new BufferedReader(fr);
            String line;
            boolean loginSuccess = false;

            // 逐行读取文件，验证用户名和密码
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    loginSuccess = true;
                    break;
                }

                // 在login方法中验证加密后的密码
//                String encryptedInput = encryptPassword(password);
//                if (parts[0].equals(username) && parts[1].equals(encryptedInput)) {
//                    // 登录成功
//                    loginSuccess = true;
//                    break;
//                }
            }


            if (loginSuccess) {
                System.out.println("登录成功！欢迎，" + username);
                // 这里可以添加登录后的操作
            } else {
                System.out.println("登录失败：用户名或密码错误");
            }

        } catch (FileNotFoundException e) {
            System.out.println("登录失败：用户数据文件不存在，请先注册");
        } catch (IOException e) {
            System.out.println("登录失败: " + e.getMessage());
        }finally {
            // 注意 关闭流时 先关闭缓存流再关闭字符流
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(fr!=null){
                try {
                    fr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    // 简单的MD5加密方法
    private static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return password; // 如果加密失败，返回原密码(不推荐)
        }
    }
}

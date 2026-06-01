package com.ch11;

import java.io.*;
import java.util.Scanner;

public class AddressBookManager {
    // 通讯录数据文件
    private static final String DATA_FILE = "contacts.txt";
    // 通讯录备份文件
    private static final String BACKUP_FILE = "contacts_backup.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. File类应用：检查并初始化文件
        File file = new File(DATA_FILE);
        try {
            if (!file.exists()) {
                System.out.println("检测到通讯录文件不存在，正在创建新文件...");
                file.createNewFile();
                System.out.println("文件创建成功！\n");
            }
        } catch (IOException e) {
            System.out.println("文件初始化失败：" + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("=== 简易通讯录管理系统 ===");
            System.out.println("1. 查看所有联系人 (字符流读取)");
            System.out.println("2. 添加新联系人 (字符流写入)");
            System.out.println("3. 备份通讯录文件 (字节流拷贝)");
            System.out.println("4. 退出系统");
            System.out.print("请输入操作编号：");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    viewContacts();
                    break;
                case 2:
                    addContact(scanner);
                    break;
                case 3:
                    backupFile();
                    break;
                case 4:
                    System.out.println("感谢使用，再见！");
                    scanner.close();
                    return;
                default:
                    System.out.println("输入有误，请重新输入。");
            }
            System.out.println();
        }
    }

    /**
     * 功能1：查看所有联系人
     * 知识点：字符流 (FileReader + BufferedReader) 高效读取文本，处理中文
     */
    private static void viewContacts() {
        System.out.println("--- 联系人列表 ---");
        // 使用 try-with-resources 自动关闭流，防止资源泄露
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            boolean isEmpty = true;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                isEmpty = false;
            }
            if (isEmpty) {
                System.out.println("通讯录为空，请先添加联系人。");
            }
        } catch (IOException e) {
            System.out.println("读取文件失败：" + e.getMessage());
        }
    }

    /**
     * 功能2：添加新联系人
     * 知识点：字符流 (FileWriter) 追加写入文本数据
     */
    private static void addContact(Scanner scanner) {
        System.out.print("请输入姓名：");
        String name = scanner.nextLine();
        System.out.print("请输入电话：");
        String phone = scanner.nextLine();

        String contactInfo = "姓名：" + name + " | 电话：" + phone;

        // 注意：FileWriter 构造方法的第二个参数为 true，表示“追加模式”，否则会覆盖原文件
        try (FileWriter fw = new FileWriter(DATA_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(contactInfo);
            bw.newLine(); // 写入换行符
            System.out.println("联系人添加成功！");
        } catch (IOException e) {
            System.out.println("写入文件失败：" + e.getMessage());
        }
    }

    /**
     * 功能3：备份通讯录文件
     * 知识点：字节流 (FileInputStream + FileOutputStream) 处理二进制数据，适用于任意文件拷贝
     */
    private static void backupFile() {
        System.out.println("正在备份文件...");
        // 使用字节流缓冲区，提高大文件拷贝效率
        try (FileInputStream fis = new FileInputStream(DATA_FILE);
             FileOutputStream fos = new FileOutputStream(BACKUP_FILE);
             BufferedInputStream bis = new BufferedInputStream(fis);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            byte[] buffer = new byte[1024]; // 1KB 的缓冲区
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            System.out.println("文件备份成功！备份文件名为：" + BACKUP_FILE);

        } catch (IOException e) {
            System.out.println("文件备份失败：" + e.getMessage());
        }
    }
}


package com.ch11;

import java.io.*;
import java.util.Scanner;

public class AddressBookManagerModify {
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
            System.out.println("4.  删除所有联系人");
            System.out.println("5.  搜索联系人");
            System.out.println("6. 退出系统");
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
                    deleteAllContacts(scanner);
                    break;
                case 5:
                    searchContact(scanner);
                    break;
                case 6:
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
     * 功能5：搜索联系人
     * @param scanner
     */
    private static void searchContact(Scanner scanner) {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("通讯录文件不存在，请先添加联系人。");
            return;
        }
        System.out.print("请输入要搜索的联系人姓名：");
        String targetName = scanner.nextLine();
        System.out.println("--- 搜索结果 ---");
        boolean found = false;
        // 3. 核心修改：读取每一行并使用 split(",") 进行分割匹配
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 使用逗号分割当前行的数据
                String[] parts = line.split(",");

                // 确保数据格式正确（有姓名和电话两个字段）
                if (parts.length >= 2) {
                    String name = parts[0];
                    String phone = parts[1];

                    // 比对姓名（这里使用 equals 进行精确匹配，若想模糊搜索可改用 contains）
                    if (name.equals(targetName)) {
                        System.out.println("找到联系人 -> 姓名：" + name + ", 电话：" + phone);
                        found = true;
                        // 如果有重名联系人，这里不 break，继续打印所有同名的人
                    }
                }
            }

            if (!found) {
                System.out.println("未找到姓名为【" + targetName + "】的联系人。");
            }

        } catch (IOException e) {
            System.out.println("读取文件时发生错误：" + e.getMessage());
        }
    }

    /**
     * 功能4：删除所有联系人
     * @param scanner
     */
    private static void deleteAllContacts(Scanner scanner) {
        System.out.print("警告：此操作将清空所有数据且无法恢复！确定要继续吗？(y/n): ");
        char confirm = scanner.next().toLowerCase().charAt(0);
        if (confirm == 'y') {
            File file = new File(DATA_FILE);
            // 判断文件是否存在
            if (file.exists()) {
                // 使用 File 类的 delete() 方法删除文件
                boolean isDeleted = file.delete();
                if (isDeleted) {
                    System.out.println("成功删除所有联系人，通讯录已清空！");
                } else {
                    System.out.println("删除文件失败，请检查文件权限或是否被占用。");
                }
            } else {
                System.out.println("通讯录本来不存在，无需删除。");
            }
            System.out.println("所有联系人已删除。");
        } else {
            System.out.println("已取消删除操作。");
        }
    }

    /**
     * 功能1：查看所有联系人
     * 知识点：字符流 (FileReader + BufferedReader) 高效读取文本，处理中文
     */
    private static void viewContacts() {
        System.out.println("--- 联系人列表 ---");
        // 建立字符输入流对象TODO
        FileReader fr = null;
        // 建立字符输入缓冲流对象TODO
        BufferedReader br = null;

        try{
            fr = new FileReader(DATA_FILE);
            br = new BufferedReader(fr);
            String line;
            boolean isEmpty = true;// 判断通讯录是否为空
            // 按行读取文件 TODO
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                isEmpty = false;
            }
            if (isEmpty) {
                System.out.println("通讯录为空，请先添加联系人。");
            }
        }catch (IOException e) {
            System.out.println("读取文件失败：" + e.getMessage());
        }finally{
            // 依次关闭字符输入流、字符缓冲输入流 TODO
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException e) {
                System.out.println("关闭流失败：" + e.getMessage());
            }

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

//        String contactInfo = "姓名：" + name + " | 电话：" + phone;
        String contactInfo = name + ", " + phone;

        // 构建字符输出流对象TODO。注意：FileWriter 构造方法的第二个参数为 true，表示“追加模式”，否则会覆盖原文件
        FileWriter fw = null;
        // 构建字符输出缓冲流对象TODO
        BufferedWriter bw = null;
        try {
            // 写入联系人对象TODO
            fw = new FileWriter(DATA_FILE, true);
            bw = new BufferedWriter(fw);
            bw.write(contactInfo);
            bw.newLine(); // 写入换行符
            System.out.println("联系人添加成功！");
        } catch (IOException e) {
            System.out.println("写入文件失败：" + e.getMessage());
        }finally{
            // 依次关闭字符输出流、字符缓冲输出流 TODO
            try {
            if (bw != null) bw.close();
            if (fw != null) fw.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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


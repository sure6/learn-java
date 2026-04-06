package com.demo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.demo.EncryptUtil.encryptPassword;


public class RegistationFrame extends JFrame {


    static final String USER_DATA_FILE="D:\\idea-workspaces\\learn-java\\user_data.txt";
    static Loginutorial jFrameTutorial;

    RegistationFrame registationFrame;

    // 构造函数
    public RegistationFrame() {
        registationFrame=this;
        // 设置窗口标题
        setTitle("用户注册");
        // 设置窗口大小
        setSize(400, 300);
        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 居中显示
        setLocationRelativeTo(null);

        // 创建主面板容器 采用网格袋布局
        JPanel mainPanel = new JPanel(new GridBagLayout());
        // 创建 GridBagConstraints 对象，用于指定每个组件在 GridBagLayout 中的布局约束
        GridBagConstraints gbc = new GridBagConstraints();
        //设置组件周围的边距（insets）
        //参数顺序：上、左、下、右（单位：像素）
        //这里设置为各边都有5像素的间距，使组件之间不会紧贴在一起
        gbc.insets = new Insets(5, 5, 5, 5);
        //设置当组件小于其显示区域时如何填充空
        //HORIZONTAL 表示水平方向填充
        //其他可选值：
        //VERTICAL：垂直方向填充
        //BOTH：水平和垂直都填充
        //NONE：不填充（默认）
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 标题标签
        JLabel titleLabel = new JLabel("用户注册", JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // 用户名标签和文本框
        JLabel usernameLabel = new JLabel("用户名:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(usernameField, gbc);

        // 密码标签和密码框
        JLabel passwordLabel = new JLabel("密码:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(passwordField, gbc);

        // 确认密码标签和密码框
        JLabel confirmPasswordLabel = new JLabel("确认密码:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(confirmPasswordLabel, gbc);

        JPasswordField confirmPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(confirmPasswordField, gbc);

        // 邮箱标签和文本框
        JLabel emailLabel = new JLabel("电子邮箱:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(emailField, gbc);

        // 注册按钮
        JButton registerButton = new JButton("注册");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registerButton, gbc);

        // 添加主面板到窗口
        add(mainPanel);

        // 注册按钮事件处理
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户名
                String username = usernameField.getText();
                // 获取密码
                String password = new String(passwordField.getPassword());
                // 获取确认密码
                String confirmPassword = new String(confirmPasswordField.getPassword());
                // 获取邮箱
                String email = emailField.getText();

                // 简单的验证
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                    // 外部类的实例 RegistationFrame.this
                    JOptionPane.showMessageDialog(RegistationFrame.this,
                            "所有字段都必须填写!", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(RegistationFrame.this,
                            "两次输入的密码不一致!", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                    // 用正则表达式方式来检验邮箱格式
                } else if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(RegistationFrame.this,
                            "请输入有效的电子邮箱地址!", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    // 这里可以添加实际的注册逻辑
                    JOptionPane.showMessageDialog(RegistationFrame.this,
                            "注册成功!", "成功", JOptionPane.INFORMATION_MESSAGE);
//                    Step2
                    //登录成功后 写用户名和密码
                    writeUserInfo(username, password);
                    // 清空表单
                    usernameField.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                    emailField.setText("");

                    // 隐藏当前窗口
                    RegistationFrame.this.setVisible(false);
                    // 对窗口内存释放， 注意JFrame.DISPOSE_ON_CLOSE程序没有结束执行 不能EXIT_ON_CLOSE （程序就停止执行）
                    registationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    // 显示登录界面
                    jFrameTutorial=new Loginutorial();
                    jFrameTutorial.setVisible(true);

                }
            }
        });
    }

    private void writeUserInfo(String userName, String password) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            // 第二个参数默认false
            fw = new FileWriter(USER_DATA_FILE, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            // 将用户名和密码写入文件，格式为：username:password
//                        pw.println(ACCOUNT[0] + ":" + ACCOUNT[1]);
            // 在register方法中加密密码
            String encryptedPwd = encryptPassword(password);
            pw.println(userName + ":" + encryptedPwd);

            System.out.println("注册成功！");

        } catch (IOException ex) {
            System.out.println("注册失败: " + ex.getMessage());
        }finally {
            if (pw!=null){
                pw.close();
            }
            if (bw!=null){
                try {
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (fw!=null){
                try {
                    fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    public static void main(String[] args) {
        new RegistationFrame().setVisible(true);
    }
}
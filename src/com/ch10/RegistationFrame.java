package com.ch10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistationFrame extends JFrame {

    static JFrameTutorial jFrameTutorial;

    RegistationFrame registationFrame;

    static final String[] ACCOUNT=new String[2];


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

        // 创建主面板 采用网格袋布局
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
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String email = emailField.getText();

                // 简单的验证
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(RegistationFrame.this,
                            "所有字段都必须填写!", "错误", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(RegistationFrame.this,
                            "两次输入的密码不一致!", "错误", JOptionPane.ERROR_MESSAGE);
                } else if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(RegistationFrame.this,
                            "请输入有效的电子邮箱地址!", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    // 这里可以添加实际的注册逻辑
                    JOptionPane.showMessageDialog(RegistationFrame.this,
                            "注册成功!", "成功", JOptionPane.INFORMATION_MESSAGE);

                    ACCOUNT[0]=username;
                    ACCOUNT[1]=password;
                    // 清空表单
                    usernameField.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                    emailField.setText("");

                    registationFrame.setVisible(false);
                    registationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jFrameTutorial=new JFrameTutorial();
                    jFrameTutorial.setVisible(true);

                }
            }
        });
    }

//    public static void main(String[] args) {
//        new RegistationFrame().setVisible(true);
//    }
}
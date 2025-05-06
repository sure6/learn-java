package com.ch09;

import javax.swing.*;
import java.awt.*;

public class NullLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Null Layout 示例");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 创建面板并设置为 null 布局
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // 创建组件并设置位置和大小
        JLabel label = new JLabel("用户名:");
        label.setBounds(50, 50, 80, 20);

        JTextField textField = new JTextField();
        textField.setBounds(140, 50, 200, 20);

        JButton button = new JButton("登录");
        button.setBounds(150, 100, 100, 30);

        // 添加组件到面板
        panel.add(label);
        panel.add(textField);
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }
}

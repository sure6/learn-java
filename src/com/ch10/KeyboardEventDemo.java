package com.ch10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEventDemo extends JFrame implements KeyListener {

    private JLabel infoLabel;
    private JTextArea logArea;

    public KeyboardEventDemo() {
        setTitle("键盘事件演示");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建界面组件
        infoLabel = new JLabel("按下键盘上的任意键...", JLabel.CENTER);
        infoLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        // 设置布局
        setLayout(new BorderLayout());
        add(infoLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // 添加键盘监听器
        addKeyListener(this);

        // 设置焦点获取
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        // 当按键被键入时触发（按下并释放）
        logArea.append("键入字符: '" + e.getKeyChar() + "'\n");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 当按键被按下时触发
        String keyText = KeyEvent.getKeyText(e.getKeyCode());
        infoLabel.setText("按下: " + keyText + " (键码: " + e.getKeyCode() + ")");

        // 特殊按键检测示例
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
            logArea.append("检测到 Ctrl+S 组合键\n");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 当按键被释放时触发
        String keyText = KeyEvent.getKeyText(e.getKeyCode());
        logArea.append("释放: " + keyText + "\n");
    }

    public static void main(String[] args) {
            KeyboardEventDemo demo = new KeyboardEventDemo();
            demo.setVisible(true);
    }
}

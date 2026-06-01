package com.ch10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private String currentInput = ""; //显示当前内容
    private double firstNumber = 0; // 第一个数字
    private String operation = ""; // 操作符
    private boolean startNewInput = true; // 是否开始新输入

    public SimpleCalculator() {
        // step1:设置JFrame窗口
        setTitle("简单计算器");
        // 设置窗口大小为300x400
        setSize(300, 400);
        // 设置关闭操作 EXIT_ON_CLOSE
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口居中
        setLocationRelativeTo(null);

        // step2:创建组件
        // 创建显示区域
        display = new JTextField();
        // 设置显示区域不可编辑
        display.setEditable(false);
        // 设置显示区域内容右对齐
        display.setHorizontalAlignment(JTextField.RIGHT);
        // 设置显示区域字体 大小为24 字体为Arial 字体样式PLAIN
        display.setFont(new Font("Arial", Font.PLAIN, 24));

        // step3:添加组件
        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        // 设置按钮面板布局为5行4列，按钮之间有5像素的水平和垂直间距
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        // 按钮文本
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "CE"
        };

        // 创建按钮并添加到面板
        for (String text : buttons) {
            // 创建按钮
            JButton button = new JButton(text);
            // 添加按钮监听器
            button.addActionListener(this);
            // 设置按钮字体为Arial，大小为18，样式为PLAIN
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(button);
        }

        // 设置布局为 BorderLayout，并添加显示区域和按钮面板
        setLayout(new BorderLayout(5, 5));
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // 设置内容面板的边距为10，10，10，10，边框为实心边框
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        // 数字按钮
        if (command.matches("[0-9]")) {
            // 处理数字按钮
            if (startNewInput) {
                currentInput = "";
                startNewInput = false;
            }
            currentInput += command;
            display.setText(currentInput);
        } else if (command.equals(".")) {
            // 处理小数点按钮
            if (startNewInput) {
                currentInput = "0";
                startNewInput = false;
            }
            if (!currentInput.contains(".")) {
                currentInput += ".";
                display.setText(currentInput);
            }
        } else if (command.matches("[+\\-*/]")) {
            // 处理运算符按钮
            if (!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
                operation = command;
                startNewInput = true;
            }
        } else if (command.equals("=")) {
            // 处理等号按钮
            if (!operation.isEmpty() && !currentInput.isEmpty()) {
                double secondNumber = Double.parseDouble(currentInput);
                double result = calculate(firstNumber, secondNumber, operation);
                display.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                operation = "";
                startNewInput = true;
            }
        } else if (command.equals("C")) {
            // 处理清除当前输入按钮
            currentInput = "";
            display.setText("0");
            startNewInput = true;
        } else if (command.equals("CE")) {
            // 处理全部清除按钮
            currentInput = "";
            firstNumber = 0;
            operation = "";
            display.setText("0");
            startNewInput = true;
        }
    }
    // 计算结果
    private double calculate(double num1, double num2, String op) {
        switch (op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    JOptionPane.showMessageDialog(this, "不能除以零", "错误", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
                return num1 / num2;
            default:
                return num2;
        }
    }

    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();
        calculator.setVisible(true);

    }
}
package com.ch10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private String currentInput = "";
    private double firstNumber = 0;
    private String operation = "";
    private boolean startNewInput = true;

    public SimpleCalculator() {
        // 设置窗口
        setTitle("简单计算器");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建显示区域
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 24));

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
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
            JButton button = new JButton(text);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(button);
        }

        // 设置布局
        setLayout(new BorderLayout(5, 5));
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // 添加边距
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            // 数字按钮
            if (startNewInput) {
                currentInput = "";
                startNewInput = false;
            }
            currentInput += command;
            display.setText(currentInput);
        } else if (command.equals(".")) {
            // 小数点
            if (startNewInput) {
                currentInput = "0";
                startNewInput = false;
            }
            if (!currentInput.contains(".")) {
                currentInput += ".";
                display.setText(currentInput);
            }
        } else if (command.matches("[+\\-*/]")) {
            // 运算符
            if (!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
                operation = command;
                startNewInput = true;
            }
        } else if (command.equals("=")) {
            // 等号
            if (!operation.isEmpty() && !currentInput.isEmpty()) {
                double secondNumber = Double.parseDouble(currentInput);
                double result = calculate(firstNumber, secondNumber, operation);
                display.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                operation = "";
                startNewInput = true;
            }
        } else if (command.equals("C")) {
            // 清除当前输入
            currentInput = "";
            display.setText("0");
            startNewInput = true;
        } else if (command.equals("CE")) {
            // 全部清除
            currentInput = "";
            firstNumber = 0;
            operation = "";
            display.setText("0");
            startNewInput = true;
        }
    }

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
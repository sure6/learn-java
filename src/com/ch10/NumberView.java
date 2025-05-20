package com.ch10;

import javax.swing.*;
import java.awt.*;

public class NumberView extends JFrame {
    public JTextField inputNumberOne,inputNumberTwo;
    public JComboBox<String> choiceFudao;
    public JTextArea textShow;
    public JButton button;
    public OperatorListener operator;
    public ComputerListener computer;
    public NumberView(){
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void init(){
        setLayout(new FlowLayout());
        Font font = new Font("宋体", Font.BOLD, 22);
        inputNumberOne = new JTextField(5);
        inputNumberTwo = new JTextField(5);
        inputNumberOne.setFont(font);
        inputNumberTwo.setFont(font);
        choiceFudao = new JComboBox<String>();
        choiceFudao.setFont(font);
        button = new JButton("计算");
        button.setFont(font);
        String[] opera={"+","-","*","/"};
        for (String s:opera){
            choiceFudao.addItem(s);
        }
        choiceFudao.setSelectedIndex(-1);
        textShow = new JTextArea(9, 30);
        textShow.setFont(font);
        operator = new OperatorListener();
        computer = new ComputerListener();
        operator.setView(this);
        computer.setView(this);
        choiceFudao.addItemListener(operator);
        choiceFudao.addActionListener(operator);
        button.addActionListener(computer);
        add(inputNumberOne);
        add(choiceFudao);
        add(inputNumberTwo);
        add(button);
        add(new JScrollPane(textShow));


    }

}

package com.ch10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class Example9_8 {
    public static void main(String[] args) {
        NumberView view = new NumberView();
        view.setBounds(100,100,600,360);
        view.setTitle("¼òµ¥¼ÆËãÆ÷");
    }
}

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
        Font font = new Font("ËÎÌå", Font.BOLD, 22);
        inputNumberOne = new JTextField(5);
        inputNumberTwo = new JTextField(5);
        inputNumberOne.setFont(font);
        inputNumberTwo.setFont(font);
        choiceFudao = new JComboBox<String>();
        choiceFudao.setFont(font);
        button = new JButton("¼ÆËã");
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


class ComputerListener implements ActionListener {
    NumberView numberView;
    String fuhao;
    public void setFuhao(String s){
        fuhao=s;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(numberView.inputNumberOne.getText());
            double num2 = Double.parseDouble(numberView.inputNumberOne.getText());
            double result=0;
            boolean isShow=true;
            switch (fuhao){
                case "+": result=num1+num2;break;
                case "-": result=num1-num2;break;
                case "*": result=num1*num2;break;
                case "/": result=num1/num2;break;
                default:isShow=false;break;
            }
            if (isShow){
                numberView.textShow.append(num1+" "+fuhao+" "+num2+" = "+result+'\n');
            }
        }catch (Exception ex){
            numberView.textShow.append("\nÇëÊäÈëÊý×Ö×Ö·û\n");
        }


    }
    public void setView(NumberView numberView) {
        this.numberView=numberView;
    }
}

class OperatorListener implements ItemListener, ActionListener {
    NumberView numberView;
    @Override
    public void actionPerformed(ActionEvent e) {
        String fuhao = numberView.choiceFudao.getSelectedItem().toString();
        numberView.computer.setFuhao(fuhao);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String fuhao = numberView.choiceFudao.getSelectedItem().toString();
        numberView.computer.setFuhao(fuhao);
    }

    public void setView(NumberView numberView) {
        this.numberView=numberView;
    }
}

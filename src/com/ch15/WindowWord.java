package com.ch15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class WindowWord extends JFrame {
    JTextField inputText, showText;
    WordPolice police;
    public WindowWord() {
        setLayout(new FlowLayout());
        inputText=new JTextField(16);
        showText=new JTextField(16);
        add(inputText);
        add(showText);
        police=new WordPolice();
        police.setJTextField(showText);
        inputText.addActionListener(police);
        setBounds(100,100,400,280);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

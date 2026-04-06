package com.ch15;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class WordPolice implements ActionListener {
    JTextField showText;
    HashMap<String ,String> hashtable;
   File file= new File("word.txt");
   Scanner sc=null;
    WordPolice(){
        hashtable=new HashMap<>();
        try {
            sc=new Scanner(file);
            while(sc.hasNext()){
                String englishWord = sc.next();
                String chineseWord = sc.next();
                hashtable.put(englishWord,chineseWord);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void setJTextField(JTextField jtf){
        this.showText=showText;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String englishWord = e.getActionCommand();
        if(hashtable.containsKey(englishWord)){
            String chineseWord=hashtable.get(englishWord);
            showText.setText(chineseWord);
        }else {
            showText.setText("没有此单词");
        }
    }
}

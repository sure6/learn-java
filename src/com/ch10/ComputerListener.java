package com.ch10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComputerListener implements ActionListener {
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
            numberView.textShow.append("\n请输入数字字符\n");
        }


    }
    public void setView(NumberView numberView) {
        this.numberView=numberView;
    }
}

package com.ch10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OperatorListener implements ItemListener, ActionListener {
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

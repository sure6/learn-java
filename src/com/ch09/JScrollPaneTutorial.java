package com.ch09;

import javax.swing.*;

public class JScrollPaneTutorial extends JFrame{
	JLabel label = new JLabel();
	JScrollPane scrollPane = new JScrollPane();
	
	public JScrollPaneTutorial(){
		label.setIcon(new ImageIcon("D:\\idea-workspaces\\learn-java\\23fruit-master1050.jpg"));
		scrollPane.setViewportView(label);
		add(scrollPane);
	}
	
	public static void main(String[] args) {
		JScrollPaneTutorial sp = new JScrollPaneTutorial();
		sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//sp.setSize(1024,800);
		sp.pack();
		sp.setVisible(true);
	}

}

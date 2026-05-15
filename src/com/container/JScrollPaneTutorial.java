package com.container;

import javax.swing.*;

public class JScrollPaneTutorial extends JFrame{
	JLabel label = new JLabel();
	JScrollPane scrollPane = new JScrollPane();
	
	public JScrollPaneTutorial(){
		label.setIcon(new ImageIcon("D:\\idea-workspaces\\learn-java\\src\\com\\gui\\staff_1024.jpg"));
		scrollPane.setViewportView(label);
		add(scrollPane);
	}
	
	public static void main(String[] args) {
		JScrollPaneTutorial sp = new JScrollPaneTutorial();
		sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sp.setSize(800,400);
		sp.pack();
		sp.setVisible(true);
	}

}

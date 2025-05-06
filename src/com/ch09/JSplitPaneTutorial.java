package com.ch09;

import javax.swing.*;

public class JSplitPaneTutorial extends JFrame{
	JLabel leftLabel = new JLabel();
	JLabel rightLabel = new JLabel();
	
	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(leftLabel),new JScrollPane(rightLabel));
	
	public JSplitPaneTutorial(){
		leftLabel.setIcon(new ImageIcon("D:\\idea-workspaces\\learn-java\\23fruit-master1050.jpg"));
		rightLabel.setIcon(new ImageIcon("D:\\idea-workspaces\\learn-java\\123.jpg"));
		add(splitPane);
	}
	
	public static void main(String[] args) {
		JSplitPaneTutorial sp = new JSplitPaneTutorial();
		sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sp.setSize(1024,600);
		sp.setVisible(true);
	}
}

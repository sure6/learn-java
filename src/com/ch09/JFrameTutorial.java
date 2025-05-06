package com.ch09;

import javax.swing.*;
import java.awt.*;

public class JFrameTutorial extends JFrame {

//	public static void main(String[] args) {
//		JFrame win1 = new JFrame("JFrame1");
//		JFrame win2 = new JFrame("JFrame2");
//		Container contentPane = win1.getContentPane();
//		contentPane.setBackground(Color.BLUE);
//		win1.setBounds(60,100,188,108);
//		win2.setBounds(260,100,188,108);
//
//
//		win2.setSize(320,240);
//		win1.setVisible(true);
//		win1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		win2.setVisible(true);
//		win2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
	private void init(){
		this.setLayout(new FlowLayout());
		// 下拉列表
		JComboBox<String> comboBoxes=new JComboBox<>();
		comboBoxes.addItem("Java");
		comboBoxes.addItem("C");
		comboBoxes.addItem("Python");
		comboBoxes.addItem("HTML5");
		// 复选框
		JCheckBox box1 = new JCheckBox("英语");
		JCheckBox box2 = new JCheckBox("数学");
		JCheckBox box3= new JCheckBox("语文");
		// 单选
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton radioButton1 = new JRadioButton("男");
		JRadioButton radioButton2 = new JRadioButton("女");
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		// 文本框
		JTextField jTextField = new JTextField( "",20);
		jTextField.setEnabled(true);
		jTextField.setEditable(true);

		//密码框
		JPasswordField passwd = new JPasswordField( "",20);
		passwd.setEnabled(true);
		passwd.setEditable(true);
//		jTextField.setText("123");
		// 按钮
		JButton button = new JButton("开始");
		JButton button2 = new JButton("结束");
		// 标签
		JLabel label = new JLabel("用户名");
		Font f1= new Font("微软雅黑",Font.BOLD,20);
		label.setFont(f1);

		JLabel label2 = new JLabel("密码");
		label2.setFont(f1);
		// 文本域
		JTextArea textArea = new JTextArea(5, 20);
		textArea.setText("这是一个示例文本区，您可以在这里输入多行文本。");

		this.add(comboBoxes);
		this.add(radioButton1);
		this.add(radioButton2);
		this.add(box1);
		this.add(box2);
		this.add(box3);
		this.add(label);
		this.add(jTextField);
		this.add(label2);
		this.add(passwd);
		this.add(button);
		this.add(button2);
		this.add(textArea);
//		this.add(new JScrollPane(textArea));
	}

	JFrameTutorial(){
		init();
//		JFrame win1 = new JFrame("常用组件");
		this.setBounds(60,100,188,108);
		this.setSize(320,240);
		this.setVisible(true);
//		win1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		JFrameTutorial jFrameTutorial = new JFrameTutorial();
		jFrameTutorial.setTitle("常用组件");
		jFrameTutorial.setResizable(true);
	}
}

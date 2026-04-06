package com.ch10;

import com.ch10.controller.StudentController;
import com.ch10.model.StudentDAO;
import com.ch10.view.StudentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// step 1 这个类具有窗口属性 自定义一个类继承JFrame
public class JFrameTutorial extends JFrame {

	static JFrameTutorial jFrameTutorial;
	private void init(){
		//定义布局
		this.setLayout(new FlowLayout());
		// 下拉列表 select
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
		ButtonGroup buttonGroup = new ButtonGroup();// 按钮组对象。 多选一必须设置
		JRadioButton radioButton1 = new JRadioButton("男");
		JRadioButton radioButton2 = new JRadioButton("女");
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		// 文本框
		JTextField jTextField = new JTextField( "",20);
		jTextField.setEnabled(true);
		jTextField.setEditable(true);

		//密码框 输入值都是**
		JPasswordField passwd = new JPasswordField( "",20);
		passwd.setEnabled(true);
		passwd.setEditable(true);
//		jTextField.setText("123");
		// 按钮
		JButton button = new JButton("登录");
		JButton button2 = new JButton("注册");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(e.getActionCommand());
				String text = jTextField.getText();
				String pwd = passwd.getText();
				if(text.equals("")||pwd.equals("")){
					System.out.println("用户名或者密码不能为空");
					return;
				}

				if(text.equals(RegistationFrame.ACCOUNT[0]) && pwd.equals(RegistationFrame.ACCOUNT[1])){
					System.out.printf("登录成功：用户名为 %s, 密码为 %s\n",text,pwd);
					JOptionPane.showMessageDialog(JFrameTutorial.this,"登录成功!","成功",JOptionPane.INFORMATION_MESSAGE);
					jFrameTutorial.setVisible(false);
					jFrameTutorial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					StudentDAO model = StudentDAO.getInstance();
					StudentView view = new StudentView();

					// 创建控制器
					new StudentController(view, model);

					// 显示视图
					view.setVisible(true);

				}else{
					System.out.printf("登录失败: 你的用户名为 %s, 密码为 %s\n",text,pwd);
					JOptionPane.showMessageDialog(JFrameTutorial.this,"登录失败!","错误",JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		button2.addActionListener(new RegisterListener());
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
	// 构造方法：对窗口进行设置
	JFrameTutorial(){
		this.init();
//		JFrame win1 = new JFrame("常用组件");
		this.setBounds(60,100,188,108);
		this.setSize(320,240);
		this.setVisible(true);
//		win1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		jFrameTutorial = new JFrameTutorial();
		jFrameTutorial.setTitle("常用组件");
		jFrameTutorial.setResizable(true);
	}

	class RegisterListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			jFrameTutorial.setVisible(false);
			jFrameTutorial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			new RegistationFrame().setVisible(true);

		}
	}
}

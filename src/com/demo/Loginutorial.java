package com.demo;

import com.demo.controller.StudentController;
import com.demo.model.StudentDAO;
import com.demo.view.StudentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.demo.EncryptUtil.encryptPassword;
import static com.demo.RegistationFrame.USER_DATA_FILE;

// step 1 这个类具有窗口属性 自定义一个类继承JFrame
public class Loginutorial extends JFrame {

	static Loginutorial jFrameTutorial;
	private void init(){
		//定义布局
		this.setLayout(new FlowLayout());

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
		// 登录事件处理
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(e.getActionCommand());
				// 获取用户名
				String text = jTextField.getText();
				// 获取密码
				String pwd = passwd.getText();
				// 用户名和密码不能为空
				if(text.equals("")||pwd.equals("")){
					System.out.println("用户名或者密码不能为空");
					return;// 结束这个方法
				}


                try {
					boolean loginSuccess= readUserInfo(text, pwd);
					if(loginSuccess){
						System.out.printf("登录成功：用户名为 %s, 密码为 %s\n",text,pwd);
						//对话框
						// 当前类是匿名类，传递第一个参数有2种方式， 第一 外部类。this, 第二种通过构造器传递this
						JOptionPane.showMessageDialog(jFrameTutorial,"登录成功!","成功",JOptionPane.INFORMATION_MESSAGE);
						//当前窗口进行隐藏
						jFrameTutorial.setVisible(false);
						// 释放当前窗口的内存资源
//						jFrameTutorial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						// MVC
						// 创建模型和视图
						StudentDAO model = StudentDAO.getInstance();
						StudentView view = new StudentView();
						// 创建控制器
						new StudentController(view, model);
						// 显示视图
						view.setVisible(true);
					}else{
						System.out.printf("登录失败: 你的用户名为 %s, 密码为 %s\n",text,pwd);
						JOptionPane.showMessageDialog(Loginutorial.this,"登录失败!","错误",JOptionPane.ERROR_MESSAGE);
					}
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


			}
		});
        // 事件源 是button
		// 监视器 RegisterListener对象
		// 处理事件接口 actionPerformed
		RegisterListener listener = new RegisterListener();
		button2.addActionListener(listener);
		// 标签
		JLabel label = new JLabel("用户名");
		Font f1= new Font("微软雅黑",Font.BOLD,20);
		label.setFont(f1);

		JLabel label2 = new JLabel("密码");
		label2.setFont(f1);
		// 文本域
		JTextArea textArea = new JTextArea(5, 20);
		textArea.setText("这是一个示例文本区，您可以在这里输入多行文本。");


		this.add(label);
		this.add(jTextField);
		this.add(label2);
		this.add(passwd);
		this.add(button);
		this.add(button2);

	}
	// 构造方法：对窗口进行设置
    public Loginutorial(){
		jFrameTutorial=this;
		this.init();
//		JFrame win1 = new JFrame("常用组件");
		this.setBounds(60,100,188,108);
		this.setSize(320,240);
		this.setVisible(true);
//		win1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		jFrameTutorial = new Loginutorial();
		jFrameTutorial.setTitle("常用组件");
		jFrameTutorial.setResizable(true);
	}

	private boolean readUserInfo(String username, String password) throws IOException {
		FileReader fr = null;
		BufferedReader br = null;

		// FileReader没有提供读取一行的方法，因此用缓冲流能够按行读取
		fr = new FileReader(USER_DATA_FILE);
		br = new BufferedReader(fr);
		String line;
		boolean loginSuccess = false;

		// 逐行读取文件，验证用户名和密码
		while ((line = br.readLine()) != null) {
			String[] parts = line.split(":");
//						if (parts.length == 2 && parts[0].equals(text) && parts[1].equals(pwd)) {
//							loginSuccess = true;
//							break;
//						}

			// 在login方法中验证加密后的密码
			String encryptedInput = encryptPassword(password);
			if (parts[0].equals(username) && parts[1].equals(encryptedInput)) {
				// 登录成功
				loginSuccess = true;
				break;
			}
		}

		return loginSuccess;
	}

	class RegisterListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// step1
//			System.out.println(e.getActionCommand());
			//当前窗口进行隐藏
			jFrameTutorial.setVisible(false);
			// 释放当前窗口的内存资源
//			jFrameTutorial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			// 显示注册窗口
			new RegistationFrame().setVisible(true);

		}
	}
}

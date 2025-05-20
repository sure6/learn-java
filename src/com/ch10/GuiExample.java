package com.ch10;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GuiExample {
	public static void main(String[] args) {
		// step1 窗口
		JFrame window = new JFrame("桂林信科");
		// step2菜单栏对象 有菜单
		JMenuBar menuBar = new JMenuBar();
		// step3 菜单
		JMenu menu1 = new JMenu("菜单1");
		JMenu menu2 = new JMenu("菜单2");
		JMenu menu3 = new JMenu("菜单3");
		//step4 定义菜单项
		JMenuItem open = new JMenuItem("打开");
		JMenuItem close = new JMenuItem("关闭");
		// ImageIcon
		ImageIcon icon = new ImageIcon("C:\\Users\\dell\\第13周\\staff_1024.jpg");
		// 图片缩放
		Image image = icon.getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT);
		Icon imageIconNew = new ImageIcon(image);
		
		ImageIcon icon2 = new ImageIcon("C:\\Users\\dell\\第13周\\true.png");
		Image image2 = icon2.getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT);
		Icon imageIconNew2 = new ImageIcon(image2);
		open.setIcon(imageIconNew);// 设置图标
		close.setIcon(imageIconNew2);
		// step5 MenuItem 放入Menu
		menu1.add(open);
		menu1.add(close);
		
		JMenuItem conf1 = new JMenuItem("配置1");
		JMenuItem conf2 = new JMenuItem("配置2");
		menu3.add(conf1);
		menu3.add(conf2);
		menu1.addSeparator();// 分割线
		// menu3放入menu1
		menu1.add(menu3);//菜单三作为菜单一的子项
		
		JMenuItem about = new JMenuItem("关于");
		JMenuItem update = new JMenuItem("更新");
		menu2.add(about);
		menu2.addSeparator();
		menu2.add(update);
		
		menuBar.add(menu1);
		menuBar.add(menu2);
		// step6 窗口添加菜单栏
		window.setJMenuBar(menuBar);
		
		// Step7 窗口设置 一定放最下面, 不然展示不了菜单
		window.setBounds(60, 100, 188, 108);
		window.setLocation(100, 100);
		window.setSize(300, 400);
		window.setVisible(true);
//		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

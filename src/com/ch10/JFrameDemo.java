package com.ch10;

import javax.swing.*;
import java.awt.*;

public class JFrameDemo {
	// GUI graph user interface图形用户界面(人机交互）
	// java.awt(有bug) -> javax.swing
    	public static void main(String[] args) {
    	// 创建窗口 有一个参数 窗口名字
		JFrame win1 = new JFrame("JFrame1");
		JFrame win2 = new JFrame("JFrame2");
		// 有了窗口 然后才能放组件，panel（画板）可以放多个组件
		Container contentPane = win1.getContentPane();
		// 对画板panel进行颜色设置
		contentPane.setBackground(Color.RED);
		// 显示在哪里，窗口出现在左边位置 屏幕左上角能看到的坐标（0,0） 60px 100px 窗口也是以左上角坐标为
		//  width 和height 表示窗口显示出来的大小
		win1.setBounds(800,540,388,308);
		win2.setBounds(260,100,188,108);


		win2.setSize(450,240);
		win1.setVisible(true);// 默认窗口不显示，默认是false
			//EXIT_ON_CLOSE关闭窗口程序结束  DISPOSE_ON_CLOSE关闭窗口只是释放程序资源
		win1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//释放窗口资源
		win2.setVisible(true);
		win1.setResizable(false);// 设置窗口的是否允许放大 默认true
		win2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 系统退出
	}
}

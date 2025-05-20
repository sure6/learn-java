package com.ch10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MousePositionTracker extends JFrame {

    private JLabel positionLabel;

    public MousePositionTracker() {
        setTitle("鼠标位置追踪器");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示

        // 创建主面板
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 创建显示位置的标签
        positionLabel = new JLabel("鼠标位置: (0, 0)", JLabel.CENTER);
        positionLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));

        // 添加组件
        panel.add(positionLabel, BorderLayout.CENTER);
        add(panel);

        // 添加鼠标移动监听器
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                updatePositionLabel(e.getX(), e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                updatePositionLabel(e.getX(), e.getY());
            }
        });
    }

    private void updatePositionLabel(int x, int y) {
        positionLabel.setText(String.format("鼠标位置: (%d, %d)", x, y));

        // 获取屏幕坐标（相对于整个屏幕）
        Point screenPoint = getLocationOnScreen();
        int screenX = screenPoint.x + x;
        int screenY = screenPoint.y + y;
        System.out.printf("窗口坐标: (%d, %d) | 屏幕坐标: (%d, %d)%n", x, y, screenX, screenY);
    }

    public static void main(String[] args) {
        MousePositionTracker tracker = new MousePositionTracker();
        tracker.setVisible(true);
    }
}

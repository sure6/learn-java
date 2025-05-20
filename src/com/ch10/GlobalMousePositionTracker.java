package com.ch10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlobalMousePositionTracker extends JFrame {

    private JLabel positionLabel;
    private Timer timer;

    public GlobalMousePositionTracker() {
        setTitle("全局鼠标位置追踪器");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        positionLabel = new JLabel("屏幕位置: (0, 0)", JLabel.CENTER);
        positionLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        panel.add(positionLabel, BorderLayout.CENTER);
        add(panel);

        // 使用Timer定期检查鼠标位置
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point mousePoint = MouseInfo.getPointerInfo().getLocation();
                positionLabel.setText(String.format("屏幕位置: (%d, %d)",
                        mousePoint.x, mousePoint.y));
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        GlobalMousePositionTracker tracker = new GlobalMousePositionTracker();
        tracker.setVisible(true);
    }
}

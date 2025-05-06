package com.ch09;
import javax.swing.*;
import java.awt.*;

public class LayeredPaneExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JLayeredPane 示例");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JLayeredPane layeredPane = new JLayeredPane();
        frame.setContentPane(layeredPane);

        // 创建三个不同颜色的面板
        JPanel panel1 = createPanel(Color.RED, 50, 50, 200, 100);
        JPanel panel2 = createPanel(Color.GREEN, 100, 100, 200, 100);
        JPanel panel3 = createPanel(Color.BLUE, 150, 150, 200, 100);

        // 添加到不同层
        layeredPane.add(panel1, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panel2, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(panel3, 250); // 自定义层

        frame.setVisible(true);
    }

    private static JPanel createPanel(Color color, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBounds(x, y, width, height);
        panel.setOpaque(true);
        return panel;
    }
}

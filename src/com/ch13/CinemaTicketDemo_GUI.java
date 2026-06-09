package com.ch13;

import javax.swing.*;
import java.awt.*;

/**
 * 阶段三：带图形界面的可视化售票系统
 */
class GUITicketTask implements Runnable {
    private int tickets = 10;
    private String channelName;
    private JTextArea logArea;  // 用于显示日志的文本区域

    public GUITicketTask(String channelName, JTextArea logArea) {
        this.channelName = channelName;
        this.logArea = logArea;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);  // 放慢节奏，方便观察
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int currentTicket = tickets;
                    final int remaining = tickets - 1;
                    tickets--;

                    // 通过 SwingUtilities 将UI更新投递到事件调度线程(EDT)
                    SwingUtilities.invokeLater(() -> {
                        logArea.append(String.format("[%s] 卖出第 %d 号票，剩余 %d 张\n",
                                channelName, currentTicket, remaining));
                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        logArea.append(String.format("[%s] 票已售罄！\n", channelName));
                    });
                    break;
                }
            }
        }
    }
}

public class CinemaTicketDemo_GUI extends JFrame {
    private JTextArea logArea;
    private JButton startBtn;
    private JButton clearBtn;

    public CinemaTicketDemo_GUI() {
        initUI();
    }

    private void initUI() {
        setTitle("电影院售票系统 - 多线程演示");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 日志显示区域
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(logArea);

        // 按钮面板
        startBtn = new JButton("开始售票");
        clearBtn = new JButton("清空日志");

        JPanel btnPanel = new JPanel();
        btnPanel.add(startBtn);
        btnPanel.add(clearBtn);

        // 布局
        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        // 按钮事件
        startBtn.addActionListener(e -> startSelling());
        clearBtn.addActionListener(e -> logArea.setText(""));
    }

    private void startSelling() {
        logArea.setText("=== 开始新一轮售票 ===\n");
        startBtn.setEnabled(false);

        // 创建共享任务
        GUITicketTask task = new GUITicketTask("共享任务", logArea);

        // 创建并启动3个售票线程
        new Thread(task, "APP渠道").start();
        new Thread(task, "微信渠道").start();
        new Thread(task, "自助机渠道").start();

        // 售票结束后恢复按钮
        new Thread(() -> {
            try {
                Thread.sleep(3000);  // 等待售票完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> startBtn.setEnabled(true));
        }).start();
    }

    public static void main(String[] args) {
        // 在事件调度线程中创建和显示GUI
        SwingUtilities.invokeLater(() -> {
            CinemaTicketDemo_GUI demo = new CinemaTicketDemo_GUI();
            demo.setVisible(true);
        });
    }
}



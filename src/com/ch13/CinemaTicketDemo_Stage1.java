package com.ch13;

/**
 * 阶段一：不加锁的售票任务（会出现超卖问题）
 */
class TicketTask implements Runnable {
    // 共享资源：10张电影票
    private int tickets = 10;
    // 用于标识售票渠道名称
    private String channelName;

    public TicketTask(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void run() {
        while (true) {
            // 模拟网络延迟，让线程切换更频繁，更容易暴露问题
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            synchronized (this) {

                // 判断是否还有票
                if (tickets > 0) {
                    // 模拟出票处理时间
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 扣减票数并出票
                    System.out.println(channelName + " 卖出了第 " + tickets + " 号票，剩余：" + (tickets - 1) + " 张");
                    tickets--;
                } else {
                    System.out.println(channelName + "：票已售罄，停止售票！");
                    break;
                }
//            }

        }
    }
}

public class CinemaTicketDemo_Stage1 {
    public static void main(String[] args) {
        // 创建一个共享的售票任务对象
        TicketTask task = new TicketTask("共享任务");

        // 创建3个线程模拟3个购票渠道
        Thread app = new Thread(task, "APP渠道");
        Thread wechat = new Thread(task, "微信渠道");
        Thread machine = new Thread(task, "自助机渠道");

        // 启动线程
        app.start();
        wechat.start();
        machine.start();
    }
}


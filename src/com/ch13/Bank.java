package com.ch13;

import javax.swing.table.TableRowSorter;

public class Bank implements Runnable{
    int money=200;
    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("会计")) {
            saveOrTake(300);
        }else if(Thread.currentThread().getName().equals("出纳")){
            saveOrTake(150);
        }
    }

    public synchronized void saveOrTake(int amount) {
        if(Thread.currentThread().getName().equals("会计")){
            for (int i = 1; i <=3; i++) {
                money=money+amount/3;
                System.out.println(Thread.currentThread().getName()+"存入"+amount/3+",账上有"+money+"万,休息一会再存");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        }else if (Thread.currentThread().getName().equals("出纳")){
            for(int i = 1; i <=3; i++) {
                int amoutMoney=0;
                if(money>=500){
                    amoutMoney=amount/2;
                }else if(money>=400&&money<500){
                    amoutMoney=amount/3;
                }else if(money>=200&&money<400){
                    amoutMoney=amount/5;
                }else if(money<200){
                    amoutMoney=amount/100;
                }
                money=money-Math.min(amoutMoney,money);
                System.out.println(Thread.currentThread().getName()+"取出"+amoutMoney+"账上有"+money+"万，休息一会再取");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        }
    }
}

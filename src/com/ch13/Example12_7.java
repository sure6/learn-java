package com.ch13;

public class Example12_7 {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.setMoney(200);

        Thread accountant = new Thread(bank);
        Thread cashier = new Thread(bank);
        accountant.setName("会计");
        cashier.setName("出纳");
        accountant.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        cashier.start();
    }
}

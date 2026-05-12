package com.ch07.exception;

public class Example7_6 {
    public static void main(String[] args) {
        Bank bank = new Bank();
        try{
            bank.income(200,-100);
            bank.income(300,-100);
            bank.income(400,-100);

            System.out.printf("银行目前有%d\n",bank.getMoney());
        } catch (BankException e) {
            e.printStackTrace();
        }
    }
}

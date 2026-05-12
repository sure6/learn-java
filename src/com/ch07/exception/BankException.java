package com.ch07.exception;

// 自定义异常 只需要 继承jdk内的Exception
public class BankException extends Exception{
    String message;
    public BankException(int m,int n){
        message="入账资金"+m+"是负数或者支出"+n+"是正数，不符合系统要求";
    }
    public String warnMess(){
        return message;
    }
}



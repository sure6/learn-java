package com.ch07.case2;

// 继承Exception，表示这是一个编译时异常，调用者必须处理
public class AccountLockedException extends Exception {
    public AccountLockedException(String message) {
        super(message);
    }
}

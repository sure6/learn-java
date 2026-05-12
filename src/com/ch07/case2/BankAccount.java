package com.ch07.case2;

public class BankAccount {
    private String accountNumber;
    private double balance;
    private boolean isLocked; // 账户锁定状态

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.isLocked = false; // 默认账户未锁定
    }

    public double getBalance() {
        return balance;
    }

    // --- 内部类：交易记录器 ---
    public class TransactionLogger {
        // 内部类可以直接访问外部类的私有成员
        public void logDeposit(double amount) {
            System.out.println("账户[" + accountNumber + "] 存入: " + amount);
        }
        public void logWithdraw(double amount) {
            System.out.println("账户[" + accountNumber + "] 取出: " + amount);
        }
    }
    // 获取内部类对象的方法
    public TransactionLogger getLogger() {
        return new TransactionLogger();
    }

    // 存款方法，可能抛出账户锁定异常
    public void deposit(double amount) throws AccountLockedException {
        if (isLocked) {
            throw new AccountLockedException("账户已锁定！无法进行存款操作。");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("存款金额必须大于0");
        }
        balance += amount;
        getLogger().logDeposit(amount); // 记录日志
    }

    // 锁定账户
    public void lockAccount() {
        this.isLocked = true;
        System.out.println("账户[" + accountNumber + "] 已锁定");
    }

    // 解锁账户
    public void unlockAccount() {
        this.isLocked = false;
        System.out.println("账户[" + accountNumber + "] 已解锁");
    }

    //业务方法抛出异常
    public void withdraw(double amount) throws InsufficientFundsException, AccountLockedException {
        if (isLocked) {
            throw new AccountLockedException("账户已锁定！无法进行取款操作。");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("取款金额必须大于0"); // 运行时异常
        }
        if (amount > balance) {
            // 抛出异常
            throw new InsufficientFundsException("余额不足！当前余额: " + balance + ", 尝试取款: " + amount);
        }
        balance -= amount;
        getLogger().logWithdraw(amount); // 记录日志
    }

    // 程序入口 - 包含测试用例
    public static void main(String[] args) {
        System.out.println("========== 测试用例开始 ==========\n");
        
        // 测试用例1：正常取款（预期：成功，余额减少）
        testCase1_NormalWithdraw();
        
        // 测试用例2：取款金额 > 余额（预期：捕获 InsufficientFundsException）
        testCase2_InsufficientFunds();
        
        // 测试用例3：取款金额为负数（预期：捕获 IllegalArgumentException）
        testCase3_NegativeAmount();
        
        System.out.println("\n========== 测试用例结束 ==========");
    }
    
    // 测试用例1：正常取款
    private static void testCase1_NormalWithdraw() {
        System.out.println("【测试用例1】正常取款");
        System.out.println("预期：成功，余额减少");
        System.out.println("-----------------------------------");
        
        BankAccount account = new BankAccount("123456", 1000.0);
        System.out.println("初始余额：" + account.getBalance());
        
        try {
            double withdrawAmount = 500.0;
            System.out.println("尝试取款：" + withdrawAmount);
            account.withdraw(withdrawAmount);
            System.out.println("? 取款成功！当前余额：" + account.getBalance());
            System.out.println("? 测试通过：余额从1000.0减少到500.0\n");
        } catch (InsufficientFundsException | AccountLockedException e) {
            System.err.println("? 测试失败：" + e.getMessage() + "\n");
        } catch (Exception e) {
            System.err.println("? 测试失败：发生意外异常\n");
            e.printStackTrace();
        }
    }
    
    // 测试用例2：取款金额 > 余额
    private static void testCase2_InsufficientFunds() {
        System.out.println("【测试用例2】取款金额 > 余额");
        System.out.println("预期：捕获 InsufficientFundsException，提示余额不足");
        System.out.println("-----------------------------------");
        
        BankAccount account = new BankAccount("789012", 500.0);
        System.out.println("初始余额：" + account.getBalance());
        
        try {
            double withdrawAmount = 800.0;
            System.out.println("尝试取款：" + withdrawAmount);
            account.withdraw(withdrawAmount);
            System.err.println("? 测试失败：应该抛出InsufficientFundsException，但未抛出\n");
        } catch (InsufficientFundsException e) {
            System.err.println("? 捕获到InsufficientFundsException: " + e.getMessage());
            System.out.println("? 测试通过：成功捕获余额不足异常\n");
        } catch (AccountLockedException e) {
            System.err.println("? 测试失败：捕获到意外的AccountLockedException: " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.err.println("? 测试失败：捕获到意外的异常\n");
            e.printStackTrace();
        }
    }
    
    // 测试用例3：取款金额为负数
    private static void testCase3_NegativeAmount() {
        System.out.println("【测试用例3】取款金额为负数");
        System.out.println("预期：捕获 IllegalArgumentException");
        System.out.println("-----------------------------------");
        
        BankAccount account = new BankAccount("345678", 1000.0);
        System.out.println("初始余额：" + account.getBalance());
        
        try {
            double withdrawAmount = -100.0;
            System.out.println("尝试取款：" + withdrawAmount);
            account.withdraw(withdrawAmount);
            System.err.println("? 测试失败：应该抛出IllegalArgumentException，但未抛出\n");
        } catch (IllegalArgumentException e) {
            System.err.println("? 捕获到IllegalArgumentException: " + e.getMessage());
            System.out.println("? 测试通过：成功捕获非法参数异常\n");
        } catch (InsufficientFundsException | AccountLockedException e) {
            System.err.println("? 测试失败：捕获到意外的异常: " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.err.println("? 测试失败：捕获到意外的异常\n");
            e.printStackTrace();
        }
    }
}




package com.ch07.exception;

public class Geeks {

    // throws在方法参数后抛出多个异常，每个异常用逗号分开
    public static void sub(int a,int b) throws ArithmeticException,Exception  {
            if (b==0){
                // 抛出某个异常对象， 可以单独写， 也可以写在捕获语句块
                throw new ArithmeticException();
            }
            int ans=a/b;
            System.out.println("答案:"+ans);


    }

    public static void main(String[] args)  {
        // try不能单独写，至少要一个catch和finally
        try{
            sub(10,0);

        }

        catch (ArithmeticException exception){
            exception.printStackTrace();
//            System.out.println("除数不能为0");
        }
        // 其他异常（系统自带，自定义）一定写在Exception类之上
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            System.out.println("不管try语句中是否出错，finally里的语句都要执行");
        }
//        sub(10,0);

    }
}

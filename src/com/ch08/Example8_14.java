package com.ch08;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//regex regular expression
public class Example8_14 {
    // 用scanner和switch以及正则去实现 用户输入3，6,7题目然后得到的结果是否正确

    public static void main(String[] args) {
//         /^[1-9][0-9]{4,10}$/    qq号匹配
//         /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/  邮箱匹配
//         /^\S*(?=\S{6,})(?=\S*\d)(?=\S*[A-Z])(?=\S*[a-z])(?=\S*[!@#$%^&*? ])\S*$/   已插入正则: "密码强度校验，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符"

//         /^(?:(?:\+|00)86)?1[3-9]\d{9}$/ 手机号匹配
        // 匹配的字符串
        String dataSource="市话76.8元,长途167.38元,短信12.68元, 其他20元";
        // 定义规则表达式
        String regex="-?[1-9][0-9]*[.]?[0-9]*"; // 匹配数字
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(dataSource);
        double sum=0;
        while (m.find()) {
            String item=m.group();
            System.out.println(item);
            sum+=Double.parseDouble(item);
        }
        System.out.println("账单总价格："+sum);

        System.out.println("89762.34".matches("[0-9.]+"));
        System.out.println("3.1415926".matches("[0-9]+[.]{1}[0-9]+"));
        System.out.println("java88_hello_99".matches("[a-zA-Z|0-9|_]+"));

        System.out.println("\t\bABC".length());
        System.out.println(new String("abc")=="abc");
    }
}

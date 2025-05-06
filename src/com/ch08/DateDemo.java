package com.ch08;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateDemo {
    public static void main(String[] args) {
        LocalDate dateNow=LocalDate.now();
        System.out.println(dateNow.getYear()+"年 "+dateNow.getMonth()+"月 "+dateNow.getDayOfMonth());
        System.out.println("当前年是第几天："+dateNow.getDayOfYear());
        System.out.println("当前年的天数："+dateNow.lengthOfYear());
        System.out.println("当前月的天数："+dateNow.lengthOfMonth());
        System.out.println("是否是闰年："+dateNow.isLeapYear());

        System.out.println("当前月份加指定个月："+dateNow.plusMonths(16));
        System.out.println("当前日加指定个天："+dateNow.plusDays(16));

        LocalDateTime nowTime = LocalDateTime.now();
        System.out.print(nowTime.getYear()+"年"+nowTime.getMonthValue()+"月"+nowTime.getDayOfMonth()+"日 "+nowTime.getDayOfWeek());
        System.out.println(" "+nowTime.getHour()+":"+nowTime.getMinute()+":"+nowTime.getSecond()+"."+nowTime.getNano());

        LocalDate localDate = LocalDate.of(2025, 5, 30);
        // compareTo 按照年，月，日逐级比较，优先返回不一样位置的的差值
        System.out.println(localDate.compareTo(dateNow));
        System.out.println(dateNow.isBefore(localDate));
        System.out.println(dateNow.isAfter(localDate));
        System.out.println(dateNow.equals(localDate));
        LocalDateTime localDateTime = LocalDateTime.of(2025, 4, 30,12,12,12,12000000);


        //计算2个日期的差值
        long days = dateNow.until(localDate, ChronoUnit.YEARS);
        System.out.println(days);

        // 格式化
//        String pattern="yyyy-MM-dd HH:mm:ss";
        String pattern="%tY-%<tm-%<td, %<tA,%<tT";
        String s = String.format(pattern, nowTime); //默认根据当前系统地区设定
        System.out.println(s);
        s=String.format(Locale.US,pattern,nowTime);
        System.out.println(s);
        s=String.format(Locale.JAPAN,pattern,nowTime);
        System.out.println(s);

    }


}

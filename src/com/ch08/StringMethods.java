package com.ch08;

public class StringMethods {
    public static void main(String[] args) {
       String s1="桂林信息科技学院科技科技";
       String s2=new String("桂林信息科技学院");

       String pic="giit.png";
//       String a="\hello";

       System.out.println(s1.length());
       // 比较String内容 必须使用equals方法，不能使用==
        System.out.println(s1.equals(s2)); // equals 比较2个对象，重写equals 重写hashcode

        System.out.println(s1.startsWith("桂林市"));
        System.out.println(s1.endsWith("学院"));
        System.out.println(s1.endsWith(".png"));

        String s3="abd"; // a b d-c char //ASCII
        String s4="abc";
        // 如果大于s4则返回正值
        System.out.println(s3.compareTo(s4));

        System.out.println(s1.contains("信息"));

        System.out.println(s1.indexOf("科技"));// 判断第一次出现位置 //4
        System.out.println(s1.indexOf("科技",6));//从fromindex后开始出现第一次的索引位置 //8

        System.out.println(s1.lastIndexOf("信息"));

        System.out.println(s1.substring(1,3));// 返回新字符串，[start,end)
        String s5="    桂林信息科技学院   ";
        System.out.println(s5.trim());

        String num1="123";
        int a =Integer.parseInt(num1);
        String.valueOf(a);
        // 例子4自己去做
        System.out.println(Integer.toBinaryString(a));// 转换成2进制 1101
        System.out.println(Integer.toHexString(a));// 16进制

        String s7="F:\\第11周\\ch08";
        String s6 = s7.replaceAll("\\\\", "#");
        System.out.println(s6);
        String[] s = s7.split("\\\\");
        for (String str:s){
            System.out.println(str);
        }

        System.out.println("-----------");
        char[] arr={'桂','林','市'};
        System.out.println(new String(arr,0,2));
        // 如何大量要对字符串进行修改操作，用stringbuffer(线程安全）或者stringBuilder（线程不安全）， 如果string ,浪费内存空间，增加空间复杂度
        StringBuffer buffer = new StringBuffer();
        StringBuilder sb=new StringBuilder();
        StringBuilder append1 = sb.append("桂").append("林");
        StringBuffer append = buffer.append("a").append("p").append("p");
        System.out.println(append.toString());
        System.out.println(append1.toString());
    }
}

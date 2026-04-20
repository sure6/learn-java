package com.leesure;

import java.util.Scanner;



public class GuilinQuiz {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("问题一：“桂林山水甲天下”的下一句是什么？");
        System.out.println("A. 阳朔山水甲桂林");
        System.out.println("B. 绝妙漓江泛秋图");
        System.out.println("C. 玉碧罗青意可参");
        System.out.println("D. 不如武夷一小丘");
        System.out.print("请输入你的答案（A/B/C/D）：");

        String answer = scanner.nextLine().trim().toUpperCase();

        if (DigestUtil.sha256(answer).equals("6b23c0d5f35d1b11f9b683f0b0a617355deb11277d91ae091d399c655b87940d")) {
            System.out.println("正确！");
        } else {
            System.out.println("错误!");
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("问题二推理：\"人都会犯错，我是人，所以我会犯错\",这个亚里士多德三段式中\"我是人\"是");
        System.out.println("A. 推导关键");
        System.out.println("B. 结论");
        System.out.println("C. 大前提");
        System.out.println("D. 小前提");
        System.out.print("请输入你的答案（A/B/C/D）：");
        String answer2 = scanner.nextLine().trim().toUpperCase();
        if (DigestUtil.sha256(answer2).equals("3f39d5c348e5b79d06e842c114e6cc571583bbf44e4b0ebfda1a01ec05745d43")) {
            System.out.println("正确!");
        } else {
            System.out.println("错误!");
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("问题三推理：更新:自动");
        System.out.println("A. 软件:下载");
        System.out.println("B. 手机:崭新");
        System.out.println("C. 机械:修理");
        System.out.println("D. 老化:自然");
        System.out.print("请输入你的答案（A/B/C/D）：");
        String answer3 = scanner.nextLine().trim().toUpperCase();
        if (DigestUtil.sha256(answer3).equals("3f39d5c348e5b79d06e842c114e6cc571583bbf44e4b0ebfda1a01ec05745d43")) {
            System.out.println("正确!");
        } else {
            System.out.println("错误!");
        }
        scanner.close();
    }
}

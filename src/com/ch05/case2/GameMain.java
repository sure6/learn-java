package com.ch05.case2;

// GameMain.java
public class GameMain {
    public static void main(String[] args) {
        System.out.println("=== 欢迎来到王者峡谷 (Java版) ===\n");

        // 1. 创建对象 (实例化)
        // 小乔 (法师)
        Mage xiaoQiao = new Mage("小乔", 2500);
        // 廉颇 (坦克)
        Tank lianPo = new Tank("廉颇", 4500);

        // 2. 展示初始状态
        System.out.println("【对战开始】");
        System.out.println(xiaoQiao.getName() + " (HP:" + xiaoQiao.getCurrentHp() + ") vs " + lianPo.getName() + " (HP:" + lianPo.getCurrentHp() + ")");
        System.out.println("--------------------------------");

        // 3. 模拟回合

        // 小乔买装备
        xiaoQiao.buyItem("博学者之怒", 400);

        // 小乔放技能
        xiaoQiao.releaseSkill();

        // 廉颇放技能 (获得护盾)
        lianPo.releaseSkill();

        System.out.println("--------------------------------");

        // 小乔攻击廉颇 (测试坦克的护盾逻辑)
        System.out.println("\n>>> 第一回合交锋：");
        xiaoQiao.attack(lianPo); // 小乔打廉颇
        xiaoQiao.attack(lianPo); // 再打一次，可能打破护盾

        System.out.println("\n>>> 第二回合交锋：");
        // 廉颇反击
        lianPo.attack(xiaoQiao);
        lianPo.attack(xiaoQiao);
        lianPo.attack(xiaoQiao);

        System.out.println("\n=== 战斗结束 ===");
    }
}


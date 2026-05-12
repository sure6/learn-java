package com.ch05.case2;

// Mage.java
public class Mage extends Hero {
    private int mana; // 法师特有属性：蓝量

    public Mage(String name, int maxHp) {
        super(name, maxHp); // 调用父类构造器
        this.mana = 500;
    }

    // 重写父类的释放技能方法 (多态体现)
    @Override
    public void releaseSkill() {
        if (this.mana >= 100) {
            // 扣蓝
            this.mana -= 100;
            System.out.println("Mage法师： " + this.getName() + " 释放了大招！造成巨额魔法伤害！(消耗蓝量100)");
            // 假设技能伤害很高
            // 实际游戏中这里会调用具体的伤害逻辑
        } else {
            // 蓝量不足
            System.out.println("Mage法师： " + this.getName() + " 蓝量不足，无法释放技能！");
        }
    }

    public void recoverMana() {
        this.mana += 50;
        System.out.println("Mage法师： " + this.getName() + " 回复了 50 点蓝量。");
    }
}

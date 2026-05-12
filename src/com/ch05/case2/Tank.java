package com.ch05.case2;

// Tank.java
public class Tank extends Hero {
    private int shield; // 坦克特有：护盾值

    public Tank(String name, int maxHp) {
        super(name, maxHp);
        this.shield = 0;
    }

    @Override
    public void releaseSkill() {
        this.shield += 500;
        System.out.println("坦克： " + this.getName() + " 开启大招！获得 500 点护盾！");
    }

    // 重写受伤逻辑：坦克有护盾先扣护盾
    @Override
    public void takeDamage(int amount) {
        if (this.shield > 0) {
            if (this.shield >= amount) {
                // 承受的伤害小于等于护盾值
                this.shield -= amount;
                System.out.println("坦克： " + this.getName() + " 的护盾抵挡了伤害！剩余护盾: " + this.shield);
            } else {
                // 承受的伤害大于护盾值
                int remainingDamage = amount - this.shield;
                this.shield = 0;
                System.out.println("坦克： " + this.getName() + " 的护盾破碎！剩余伤害 " + remainingDamage + " 穿透护盾。");
                // 调用父类方法扣血
                super.takeDamage(remainingDamage);
            }
        } else {
            super.takeDamage(amount);
        }
    }
}


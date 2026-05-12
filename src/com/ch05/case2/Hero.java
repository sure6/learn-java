package com.ch05.case2;

/**
 *
 * 王者荣耀案例：
 *
 * 理解继承（Inheritance）：不同职业（坦克、法师、刺客）的共性与特性。
 * 初步接触多态（Polymorphism）：不同英雄释放技能的表现不同。
 *
 * 编程概念	   王者荣耀对应概念	  解释
 * 类 (Class)	英雄模板	        比如“英雄”这个概念，定义了所有英雄都有名字、血量、蓝量。
 * 对象 (Object)	具体的英雄实例	    比如“我方的一级鲁班七号”或“敌方的满级韩信”。
 * 属性 (Field)	英雄状态	        名字、当前血量、最大血量、等级、金币。
 * 方法 (Method)	行为/技能	    attack() (普攻), releaseSkill() (放技能), buyItem() (买装备)。
 * 封装	        血条保护机制	    玩家不能直接写 hero.hp = -100 秒杀对方，必须调用 takeDamage(100)，其中可以包含防御力计算逻辑。
 * 继承	        职业分类	        “坦克”、“法师”都继承自“英雄”父类，但坦克血厚，法师蓝多。
 */
public class Hero {
    // 1. 属性 (封装：私有化，防止外部直接修改)

    private String name;    // 英雄名字
    private int maxHp;          // 最大血量
    private int currentHp;      // 当前血量
    private int level;          // 等级
    private int gold;           // 金币

    // 2. 构造方法 (用来创建具体的英雄对象)
    public Hero(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp; // 刚出生时满血
        this.level = 1;
        this.gold = 500;        // 初始金币
    }

    // 3. Getter 和 Setter (受控访问)
    public String getName() {
        return name;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    // 4. 行为方法
    // 普通攻击
    public void attack(Hero target) {
        if (this.currentHp <= 0) {
            System.out.println(this.name + " 已经阵亡，无法攻击！");
            return;
        }
        // 简单伤害公式：基础伤害 + 等级加成
        int damage = 50 + (this.level * 5);
        System.out.println(this.name + " 对 " + target.getName() + " 发起了普通攻击，造成 " + damage + " 点伤害！");
        target.takeDamage(damage);
    }

    // 受到伤害 (封装的核心：在这里可以加入护甲计算逻辑)
    public void takeDamage(int amount) {
        // 模拟护甲减免：假设减免20%
        int realDamage = (int) (amount * 0.8);
        this.currentHp -= realDamage;

        if (this.currentHp <= 0) {
            this.currentHp = 0;
            System.out.println("kill: " + this.name + " 被击杀了！(剩余血量: 0)");
        } else {
            System.out.println("   -> " + this.name + " 剩余血量: " + this.currentHp + "/" + this.maxHp);
        }
    }

    // 购买装备
    public void buyItem(String itemName, int price) {
        if (this.gold >= price) {
            this.gold -= price;
            System.out.println("购物车：" + this.name + " 购买了 [" + itemName + "]，剩余金币: " + this.gold);
            // 这里可以扩展：买完装备增加攻击力或血量
            if ("博学者之怒".equals(itemName)) {
                // 举例：有些装备加血
                this.maxHp += 200;
                this.currentHp += 200;
                System.out.println("   -> 血量提升，最大生命值增加 200！");
            }
        } else {
            System.out.println("购物车：" + this.name + " 金币不足，无法购买 " + itemName);
        }
    }

    // 虚方法：释放技能 (为多态做准备)
    public void releaseSkill() {
        System.out.println(this.name + " 释放了一个通用技能！");
    }
}


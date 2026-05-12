package com.ch07;

public class RedCowFarm {
    static String farmName; // 静态成员变量
    // int a=10; // 内部类能有效访问
    RedCow cow;// 声明内部类的引用

    RedCowFarm() {

    }

    RedCowFarm(String s) {
        cow = new RedCow(1501, 112, 5000);
        farmName = s;
    }

    //外部类方法
    public void showCowMess() {

        cow.speak();
    }

    // 内部类可以用static修饰， 也可以用protected private修饰 但是没有任何意义，内部类主要给外嵌类使用， 访问控制符没有任何意义
    class RedCow {
//        static int a=10;
        String cowName = "红牛";
        int height, weight, price;

        RedCow(int h, int w, int p) {
            height = h;
            weight = w;
            price = p;
        }

        void speak() {
            System.out.println("偶是" + cowName + ",身高" + height + "cm体重：" + weight + "kg，生活在" + farmName);
            showCowMess();
        }
    }


    public static void main(String[] args) {
//        RedCowFarm redCowFarm = new RedCowFarm("红牛农场");
//        redCowFarm.showCowMess();
//        redCowFarm.cow.speak();
//        RedCowFarm.RedCow rc=new RedCowFarm.RedCow(1,3,4,);
        RedCow rc = new RedCowFarm("红牛农场").new RedCow(1, 2, 3);
        rc.speak();
    }
}


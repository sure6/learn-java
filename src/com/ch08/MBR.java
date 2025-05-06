package com.ch08;

import java.util.Random;

public class MBR {
    public static void main(String[] args) {
        System.out.println(Math.PI);
        System.out.println(Math.max(3,4));
        System.out.println(Math.random());
        System.out.println((int)(Math.random()*100+1));


        /**
         * Random类中实现的随机算法是伪随机，也就是有规则的随机。在进行随机时，随机算法的起源数字称为种子数(seed)，在种子数的基础上进行一定的变换，从而产生需要的随机数字。
         * 相同种子数的Random对象，相同次数生成的随机数字是完全相同的。也就是说，两个种子数相同的Random对象，第一次生成的随机数字完全相同，第二次生成的随机数字也完全相同。
         * 这点在生成多个随机数字时需要特别注意。
         */
        Random random = new Random();
//        random.setSeed(System.currentTimeMillis());
        random.setSeed(10);
        int i = random.nextInt();
        System.out.println("i1:"+i);
        int i2 = random.nextInt(100);
        System.out.println(i2);
        int i3 = random.nextInt();
        System.out.println("i3:"+i3);

        double i4 = random.nextDouble();
        System.out.println(i4);
        boolean i5 = random.nextBoolean();
        System.out.println(i5);

        String  s=String.format("%d,%o,%x,%X",703576,703576,703576,703576);
        System.out.println(s);
        String  s1=String.format("%-12f,%e",13579.98,13579.98);
        System.out.println(s1);

    }
}

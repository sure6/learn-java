package com.ch08;

public class Example8_1 {
    public static void main(String[] args) {
        String hello="你好";// 内存创建1个对象
//        String hello2=new String("你好"); // 内存创建2个对象
        String testOne="你"+"好";// 创建了新的对象你好 你，好以及你好 3个不同String对象
//        char ch='你';
        int address = System.identityHashCode("你好");
//        System.out.printf("\"你好\"的引用：%s\n",address);
//        address = System.identityHashCode(hello);
//        System.out.printf("hello的引用：%s\n",address);
//        address = System.identityHashCode(testOne);
//        System.out.printf("testOne的引用：%s\n",address);
//        System.out.println(hello == testOne);
//        System.out.println("你好" == testOne);
//        System.out.println("你好" == hello);

        String you="你";
        String hi="好";
        String testTwo= you +hi;
        address = System.identityHashCode("你");
        System.out.printf("\"你\"的引用：%s\n",address);
        address = System.identityHashCode("好");
        System.out.printf("\"好\"的引用：%s\n",address);
        address = System.identityHashCode(testTwo);
        System.out.printf("testTwo的引用：%s\n",address);
        System.out.println(hello == testTwo); // 你用引用的方式进行字符串相加，最后得到一个新的字符串

    }
}



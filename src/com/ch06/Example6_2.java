package com.ch06;

interface ShowMessage {

    void showBrand(String s);
    default void outPutStart() {
        System.out.println("********");
    }
}

class TV implements ShowMessage{

    @Override
    public void showBrand(String s) {
        System.out.println("tvtvtv");
        System.out.println(s);
        System.out.println("tvtvtv");
    }
}

class PC implements ShowMessage{

    @Override
    public void showBrand(String s) {
        System.out.println("pcpcpc");
        System.out.println(s);
        System.out.println("pcpcpc");
    }
}

public class Example6_2 {

    public static void main(String[] args) {
        ShowMessage sm;
        sm = new TV();
        sm.showBrand("小米牌电视机");
        sm = new PC();
        sm.outPutStart();
        sm.showBrand("华为个人电脑");
        sm.outPutStart();
    }
}
package com.leesure;

public class OuterCIass {
    private int a;
    private void method () {
        final int methodVal = 3;
        class Tempinner {
            int cccc=methodVal;//A
            Inner inner = new Inner();//B
            void doTest() {
                inner.c =5;//c
            }
        }
    }
    class Inner {
        int c = a;
        void innerMethod() {
            method();
        }
    }
}

class TestBasicVar{
    float a=10;
    double b=10.0;

}
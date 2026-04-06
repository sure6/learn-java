package com.ch13;

public class SpeakElephant extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <= 20; i++) {
            System.out.println("大象"+i+" ");
        }
    }
}

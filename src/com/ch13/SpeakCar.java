package com.ch13;

public class SpeakCar extends Thread{

    @Override
    public void run() {
        for (int i = 0; i <= 20; i++) {
            System.out.println("轿车"+i+" ");
        }
    }
}

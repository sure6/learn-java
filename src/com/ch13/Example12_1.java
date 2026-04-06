package com.ch13;

public class Example12_1 {
    public static void main(String[] args) {
        SpeakElephant speakElephant = new SpeakElephant();
        SpeakCar speakCar = new SpeakCar();
        speakElephant.start();
        speakCar.start();
        for (int i=1; i<=15; i++){
            System.out.println("主人"+i+" ");
        }
    }
}

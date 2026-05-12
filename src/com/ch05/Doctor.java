package com.ch05;

public class Doctor  extends Human{
    public static void main(String[] args) {
        Doctor doctor = new Doctor();
        doctor.age=20;
    }

    @Override
    int min(int x, int y) {
        return x>y?y:x;
    }
    public void care(){
        System.out.println("治疗");
    }
}

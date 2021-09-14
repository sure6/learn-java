package com.leesure;

import java.util.ArrayList;

public class Eratosthenes {

    public static void getPrimeNum(int size) {
        boolean[] total = new boolean[size];
        for (int i = 2; i < size; i++) {
            total[i] = true;
//            System.out.print("total["+i+"]: "+total[i]+", ");
        }

        for (int j = 2; j <= (int) Math.sqrt(size); j++) {
            if (total[j] == true) {
                int count = 0;
                for (int k = j * j; k < size; k = j * j + j * count) {
                    total[k] = false;
                    count++;
                }
            }
        }

        for (int z = 2; z < size; z++) {
            System.out.println("total[" + z + "]: " + total[z] + ", ");
        }
    }

    public static void getPrimeNum2(int size) {
        int[] total = new int[size];
        for (int i = 2; i < size; i++) {
            total[i] = i;
        }

        for (int j = 2; j <= (int) Math.sqrt(size); j++) {
            int count = 0;
            for (int k = j * j; k < size; k = j * j + j * count) {
                total[k] = 0;
                count++;
            }
        }

        int totalNumber = 0;
//        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int a : total) {
            if (a != 0) {
                totalNumber++;
//                arrayList.add(a);
            }
        }
        System.out.println("total prime number: " + totalNumber);
//        System.out.println(arrayLis);
        int[] primeNum=new int[totalNumber];
        int primeNumIndex=0;
        for (int a = 0; a < total.length; a++) {
            if (total[a]!=0){
                primeNum[primeNumIndex]=total[a];
                primeNumIndex++;
            }
        }

        for (int c:primeNum ) {
            System.out.println(c);
        }



    }

    public static void main(String[] args) {
        getPrimeNum2(10000);
    }
}

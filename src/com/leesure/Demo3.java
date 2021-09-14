package com.leesure;

import java.util.*;

public class Demo3 {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet();
        while (true){
            Scanner scanner = new Scanner(System.in);
            int N = scanner.nextInt();
            for (int i = 0; i < N; i++) {
                int j=(int)Math.ceil(Math.random()*1000+1);
                set.add(j);
                System.out.println(j);
            }
            if (N==0){
                break;
            }
        }
        List<Integer> list = new ArrayList(set);
        Collections.sort(list);
        for (int i:list){
            System.out.println(i);
        }

    }
}

class Demo3Col{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){

            int i = scanner.nextInt();
            Set<Integer> set = new TreeSet();
            for (int j = 0; j < i; j++) {
                int num=scanner.nextInt();
                set.add(num);
            }

            List<Integer> list = new ArrayList(set);
            Collections.sort(list);
            for (int z:list){
                System.out.println(z);
            }

        }
    }
}
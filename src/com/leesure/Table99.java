package com.leesure;

public class Table99 {
    public static void main(String[] args) {
        System.out.println("Nine-by-nine Multiplication Table");
        System.out.println("---------------------------------");
        for(int i=0;i<10;i++){
            if(i==0){
                System.out.printf("%s "," ");
            }else{
                System.out.printf("%2d ", i);
            }
        }
//        System.out.printf("%s %2d %2d %2d %2d %2d %2d %2d %2d %2d"," ",1,2,3,4,5,6,7,8,9);
        System.out.println();
        System.out.println("---------------------------------");
        for (int i = 1; i < 10; i++) {
            System.out.printf("%-2s",i);
            for (int j = 1; j <= i; j++) {
                System.out.printf("%2d",i*j);
                System.out.printf("%s"," ");
            }

            System.out.println();
        }
    }
}

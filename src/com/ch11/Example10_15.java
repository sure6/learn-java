package com.ch11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Example10_15 {
    public static void main(String[] args) {
        File file = new File("D:\\idea-workspaces\\learn-java\\src\\com\\ch11\\cost.txt");
        Scanner sc = null;
        int sum = 0;
        try {
            sc=new Scanner(file);
            while (sc.hasNext()){
                try{
                    int price = sc.nextInt();
                    sum+=price;
                    System.out.println(price);
                }catch(InputMismatchException e){
                    String t = sc.next();
                }
            }
            System.out.println("total cost: "+sum+" dollar");
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        }
    }
}

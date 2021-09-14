package com.leesure;
import java.util.Scanner;
public class Demo2 {


        public static int calcChar(String s1, char ch){
            if (s1.equals(null) && s1.equals(""))return -1;
            if (s1.length()>500)return -1;
            int count =0;
            char[] cha=s1.toCharArray();
            for(int i =0; i<cha.length; i++){
                int j=cha[i]-ch;
                if(Math.abs(j)==32 || Math.abs(j)==0){
                    count++;
                }
            }
            return count;
        }
        public static void main(String[] args){
            Scanner sc=new Scanner(System.in);
            String s1=sc.nextLine();
            String ch=sc.nextLine();

            System.out.println(calcChar(s1,ch.toCharArray()[0]));
        }
}

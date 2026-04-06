package com.ch03;

public class 用1234组无重复和会不相同的数 {

	public static void main(String[] args) {
        //int x=0,y=0,z=0;
		int a=0;
        for(int i=1;i<=4;i++){
        	 for(int j=1;j<=4;j++){
        		 for(int k=1;k<=4;k++){
        			 
					if (i != j && i != k && j != k) {
						a = i * 100 + j * 10 + k;
						System.out.print(a+",");
					}
        		 }
        	 }
        }
	}

}

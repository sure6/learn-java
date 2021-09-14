package com.leesure;

public class PrimeNumberScreening {
	public static void main(String[] args) {
		// 确定查找范围
		int n = 10000;
		// 初始化BooleanIntPair数组
		BooleanIntPair[] origin = new BooleanIntPair[n - 1];
		for (int i = 0; i < origin.length; i++) {
			origin[i] = new BooleanIntPair();
			origin[i].value = i + 2;
		}

		// 将所有非质数的isPN属性set为false
		for (int i = 0; i < origin.length - 1; i++) {
			if (origin[i].isPN == false)
				continue;
			for (int j = i + 1; j < origin.length; j++) {
				if (origin[j].isPN == false)
					continue;
				if (origin[j].value % origin[i].value == 0)
					origin[j].isPN = false;
			}
		}

		// 输出质数，10个1行
		int count = 0;
		for (BooleanIntPair bip : origin) {
			if (bip.isPN==false) continue;
			System.out.print(bip.value + "\t");
			count += 1;
			if (count == 10) {
				System.out.print('\n');
				count = 0;
			}
		}
	}
}

//将数字与Boolean标记打包
class BooleanIntPair {
	boolean isPN = true;
	int value;
}
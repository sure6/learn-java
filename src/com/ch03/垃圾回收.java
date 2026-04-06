package com.ch03;

/**
 * finalize的工作原理应该是这样的：一旦垃圾收集器准备好释放对象占用的存储空间，它首先调用finalize()，而且只有在下一次垃圾收集过程中，才会真正回收对象的内存.所以如果使用finalize()，就可以在垃圾收集期间进行一些重要的清除或清扫工作.

finalize()在什么时候被调用?
有三种情况
1.所有对象被Garbage Collection时自动调用,比如运行System.gc()的时候.
2.程序退出时为每个对象调用一次finalize方法。
3.显式的调用finalize方法

除此以外,正常情况下,当某个对象被系统收集为无用信息的时候,finalize()将被自动调用,但是jvm不保证finalize()一定被调用,也就是说,finalize()的调用是不确定的,这也就是为什么sun不提倡使用finalize()的原因
 * @author Administrator
 *
 */
public class 垃圾回收 {
	
	
    @Override
	protected void finalize() throws Throwable {
		System.out.println("我被执行了..");
	}

	public static void main(String[] args) {
		new 垃圾回收();
		System.gc();
	}
}

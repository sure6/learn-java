package com.ch07;

/**
 * 在这个例子中，我们有一个外部类OuterClass，它有一个内部类InnerClass。内部类有自己的方法和变量，
 * 也可以访问外部类的方法和变量。要创建内部类的实例，首先要创建外部类的实例，
 * 然后用它来创建内部类。然后，我们可以使用内部类的实例访问内部类的方法，也可以访问外部类的方法和变量。
 */
public class OuterClass {
    private int outerVar;

    public OuterClass(int var) {
        outerVar = var;
    }

    public void outerMethod() {
        System.out.println("这是一个外部方法");
    }

    // Inner class
    public class InnerClass {
        private int innerVar;

        public InnerClass(int var) {
            innerVar = var;
        }

        public void innerMethod() {
            System.out.println("这是一个内部方法");
        }

        public void accessOuterVar() {
            System.out.println("来自内部类的外部变量: " + outerVar);
        }
    }

    public static void main(String[] args) {
        // Create an instance of the outer class
        OuterClass outer = new OuterClass(10);

        // Create an instance of the inner class
        OuterClass.InnerClass inner = outer.new InnerClass(20);

        // Access the inner class methods
        inner.innerMethod();
        inner.accessOuterVar();
    }
}
/*
在Java中使用内部类的好处是：
1.封装：内部类可以访问外部类的私有变量和方法。这有助于实现封装并提高代码的可读性。
2.代码组织：内部类允许您将相关代码组合在一起。这使您的代码更容易理解和维护。更好的访问控制：内部类可以声明为私有，
  这意味着它们只能在外部类中访问。这提供了更好的访问控制并提高了代码安全性。
3.回调：内部类通常用于在事件驱动编程中实现回调。它们提供了一种在外部类的上下文中定义和实现回调函数的方便方法。
4.多态：内部类可以用来实现多态。您可以在外部类中定义类层次结构，然后创建实现不同子类的内部类的对象。
5. 降低代码复杂性：内部类可以通过在外部类的上下文中封装复杂的逻辑和数据结构来降低代码的复杂性。
总的来说，使用内部类可以产生更加模块化、可维护和灵活的代码。

 */


package com.ch07;

/**
 * @author leesure
 */
public class StaticNestedClass {//外部类
    static String name = "joshua317";
    public static void main(String[] args) {
        StaticNestedClass outer = new StaticNestedClass();
        //方式一：内部直接访问
        Inner inner1 = new Inner();
        inner1.innerFun();
        //方式二：因为是静态内部类，可以通过类名直接访问(前提是满足访问权限)
        StaticNestedClass.Inner inner2 = new StaticNestedClass.Inner();
        inner2.innerFun();
        //方式三：通过普通成员方法，返回静态内部类的实例
        Inner inner3 = outer.getInnerInstance();
        inner3.innerFun();
        //方式四：通过静态方法，返回静态内部类的实例
        StaticNestedClass.Inner inner4 = StaticNestedClass.getInnerInstance2();
        inner4.innerFun();
    }
    static class Inner {//内部类
        static String name = "joshua317-inner";
        public void innerFun () {
            System.out.println("内部类静态成员" + name + "  外部类静态成员" + StaticNestedClass.name);
        }
    }

    /**
     * 通过方法，返回静态内部类的实例
     * @return Inner
     */
    public Inner getInnerInstance() {
        return new Inner();
    }

    public static Inner getInnerInstance2() {
        return new Inner();
    }
}

package com.ch07;
/**
 * @author leesure
 */
public class MethodLocalInnerClass {//外部类
    private String name;
    public static void main(String[] args) {
        MethodLocalInnerClass outerObj = new MethodLocalInnerClass("leesure");
        outerObj.OuterFun1();
    }

    public MethodLocalInnerClass(String name) {
        this.name = name;
    }

    public void OuterFun1() {
        System.out.println("外部类成员方法");
        //内部类
        class Inner {
            String name;
            public void InnerFun1() {
                //访问外部类的同名成员属性
                System.out.println("局部内部类的成员属性name:"+ name + " 我是外部类的成员属性name:"+MethodLocalInnerClass.this.name);
            }
            public void setName(String name) {
                this.name = name;
            }

        }
        Inner inner = new Inner();
        inner.setName("innerName");
        inner.InnerFun1();
        System.out.println("局部内部类的成员属性name：" + inner.name);
    }
}


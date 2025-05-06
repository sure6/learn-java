package com.leesure;

/*
基本要求：

1：定义相关类及方法，实现多种几何图形面积的计算，至少包括三种几何图形

2：使用到类定义、引用型数据参数传递、方法重载、访问权限等知识点。

3：程序输出包括学号和姓名
 */


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

class GeometryArea{
    // 计算几何面积并根据参数类型进行重载方法， 并引用了引用型数据参数传递
    // 计算长方形面积
    public static double computeArea(Rectangle rectangle){
        return rectangle.getWidth()*rectangle.getHeight();
    }
    // 计算三角形面积
    public double computeArea(Triangle triangle){
        return triangle.getBottom()*triangle.getHeight()/2;
    }
    // 计算梯形面积
    public double computeArea(Ladder ladder){
        return (ladder.getBottom()+ladder.getTop())*ladder.getHeight()/2;
    }
    // 计算圆形面积
    public double computeArea(Circle circle){
        return  circle.getRadius()*circle.getRadius()*Math.PI;
    }



}

// 定义长方形类和成员变量(体现面向对象的封装性）， 防止成员变量直接被访问和修改
class Rectangle{
    private double width;// 宽
    private double height;// 高

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
// 定义三角形类和成员变量
class Triangle{
    private double bottom;// 低
    private double height;// 高

    public double getBottom() {
        return bottom;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

// 定义梯形类和成员变量
class Ladder{
    private double bottom; // 下底
    private double top;// 上底
    private double height;// 高

    public double getBottom() {
        return bottom;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

// 定义梯形类和成员变量
class Circle{
    private double radius; // 半径

    // 采用构造方法传值
    public Circle(double radius) {
        this.radius = radius;
    }
    // 对外访问成员属性
   public double getRadius() {
        return radius;
   }
}



public class 第四章作业参考答案 {


    public static void main(String[] args) {
        System.out.println("学号：xxxxxxxx");
        System.out.println("姓名：张三");
        //初始化几何图形属性
        Rectangle r = new Rectangle(); //长方形
        r.setWidth(5.5);
        r.setHeight(10.3);
        Triangle t = new Triangle(); //三角形
        t.setBottom(3.3);
        t.setHeight(1.5);
        Ladder l = new Ladder(); // 梯形
        l.setTop(3.6);
        l.setHeight(1.5);
        l.setBottom(4.4);
        Circle c = new Circle(5.0); // 圆形

        GeometryArea geometryArea = new GeometryArea();
        double circleArea = geometryArea.computeArea(c);
        double rectangleArea = GeometryArea.computeArea(r);//调用静态
        double triangleArea = geometryArea.computeArea(t);
        double ladderArea = geometryArea.computeArea(l);
        System.out.println("圆形面积为："+circleArea);
        System.out.println("长方形面积为："+rectangleArea);
        System.out.println("三角形面积为："+triangleArea);
        System.out.println("梯形面积为："+ladderArea);

        // 不计算总分里：解决精度问题
        BigDecimal bd = new BigDecimal(Double.toString(circleArea));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        System.out.println("圆形面积为(四舍五入保留2位小数)："+bd.doubleValue());
        BigDecimal bd2 = new BigDecimal(rectangleArea);
        bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
        System.out.println("长方形面积为(四舍五入保留2位小数)："+bd2.doubleValue());
        BigDecimal bd3 = new BigDecimal(triangleArea);
        bd3 = bd3.setScale(2, RoundingMode.HALF_UP);
        System.out.println("三角形面积为(四舍五入保留2位小数)："+bd3.doubleValue());

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        String output = nf.format(ladderArea);
        System.out.println("梯形面积为(四舍五入保留2位小数)："+output);


    }

}

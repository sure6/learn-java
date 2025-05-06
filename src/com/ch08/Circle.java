package com.ch08;

class Circle{
    private double radius;
    public void setRadius(double radius) {
       this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

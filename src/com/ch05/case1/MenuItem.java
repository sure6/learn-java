package com.ch05.case1;

public class MenuItem {

    private String name;
    private double price;
    private String description;
    private boolean isAvailable;

    public double updatePrice(double newPrice){
        price=newPrice;
        return price;
    }
    public String getDetails() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


}

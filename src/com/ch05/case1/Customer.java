package com.ch05.case1;

public class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private int vipLevel;//1:白银会员, 2:金卡会员, 3:钻石会员
    private String address;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public void placeOrder(Order order){
        order.setCustomer(this);
        order.calculateTotal();

    }

    public void cancelOrder(Order order){
        order.setCustomer(null);
        order.updateStatus("已取消");
        System.out.println("订单取消成功");
    }

    public void addAddress(String  address){
        // 添加地址的逻辑
        this.address=address;
    }
    public String getAddress(){
        return this.address;
    }

}

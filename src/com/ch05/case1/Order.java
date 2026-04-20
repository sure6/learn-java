package com.ch05.case1;

public class Order {
    private String orderId;
    private Customer customer;
//    private String[] items;
    private MenuItem[] items;
    private double totalPrice;
    private String status;

    public MenuItem[] getItems() {
        return items;
    }

    public void setItems(MenuItem[] items) {
        this.items = items;
    }
    /**
     * 添加订单商品
     * @param item
     */
    public void addItem(MenuItem item){
        for (int i = 0; i < items.length; i++) {
            if (items[i]==null&&item.isAvailable()){
                items[i]=item;
                break;
            }
        }
    }

    /**
     * 计算订单总价
     * @return
     */
    public double calculateTotal(){
        if (items==null){
            return totalPrice;
        }
        for (MenuItem item : items) {
            if (item!=null){
                totalPrice+=item.getPrice();
            }
        }
        status="待付款";
        switch (customer.getVipLevel()){
            case 1:
                totalPrice= totalPrice*0.9;break;
            case 2:
                totalPrice= totalPrice*0.8;break;
            case 3:
                totalPrice= totalPrice*0.7;break;
        }
        return totalPrice;
    }

    /**
     * 更新订单状态
     * @param status
     */
    public void updateStatus(String status){
        this.status=status;
    }

    /**
     * 支付订单
     */
    public void pay(){
        if(status.equals("已取消"))return;
        status="制作中";
        System.out.println("订单提交成功, 订单号为: " + orderId+", 订单状态为: "+status+", 订单金额为: "+totalPrice+"元, 订单地址为: "+customer.getAddress());

        System.out.println("订单商品为: ");
        for (MenuItem item : items){
            if (item != null)System.out.println(item.getName()+" 单价："+item.getPrice()+"元，介绍："+item.getDetails());
        }

        this.updateStatus("配送中");
        System.out.println("订单状态更新为:" + this.getStatus());
        this.updateStatus("已完成");
        System.out.println("订单状态更新为:" + this.getStatus());
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

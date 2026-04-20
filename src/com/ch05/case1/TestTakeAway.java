package com.ch05.case1;

public class TestTakeAway {
    public static void main(String[] args) {
        Order order=new Order();
        order.setOrderId("123456");
        order.setItems(new MenuItem[5]);

        MenuItem menuItem = new MenuItem();
        menuItem.setName("桂林米粉");
        menuItem.setPrice(10.0);
        menuItem.setDescription("产自广西桂林的特色美食，味道鲜美");
        menuItem.setAvailable(true);
        order.addItem(menuItem);

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setName("柳州螺蛳粉");
        menuItem2.setPrice(15.0);
        menuItem2.setDescription("产自广西柳州的特色美食，味道鲜美，口感软");
        menuItem2.setAvailable(false);
        order.addItem(menuItem2);

        MenuItem menuItem3 = new MenuItem();
        menuItem3.setName("南宁老友粉");
        menuItem3.setPrice(20.0);
        menuItem3.setDescription("产自广西南宁的特色美食，味道鲜美，口感滑");
        menuItem3.setAvailable(true);
        order.addItem(menuItem3);


        Customer customer = new Customer();
        customer.setCustomerId("C123456");
        customer.setName("张三");
        customer.setPhoneNumber("12345678901");
        customer.setVipLevel(2);
        customer.addAddress("桂林信息科技学院");
        System.out.println(customer.getCustomerId());
        System.out.println(customer.getName());

        customer.placeOrder(order);
//        customer.cancelOrder(order);

        order.pay(); //


    }

}

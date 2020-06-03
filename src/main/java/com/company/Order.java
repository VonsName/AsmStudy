package com.company;

public class Order {

    public void buy() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束了");
    }
}

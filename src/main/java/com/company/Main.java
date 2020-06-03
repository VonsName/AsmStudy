package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("下面有请代理出场");

        Order order = new Order();
        order.buy();
//
//        Main main = new Main();
//        main.buy();
    }

    public void buy() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.kuang.part01_demo01;

// 基本的卖票例子

/**
 * 真正的多线程开发，公司中的开发，降低耦合性
 * 线程就是一个单独的资源类，没有任何附属的操作！
 * 1、属性
 * 2、方法
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
//        // 获取CPU核数
//        // CPU密集型，IO密集型
//        System.out.println(Runtime.getRuntime().availableProcessors());


        // 并发：多线程操作同一个资源类, 把资源类丢入线程
        Ticket ticket = new Ticket();

        // @FunctionalInterface是函数式接口，jdk1.8  lambda表达式 (参数)->{ 代码 }
        // new Thread((参数) -> {代码},"name").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket. sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();


    }
}

// 资源类 OOP
class Ticket {
    // 属性、方法
    private int number = 30;

    // 卖票的方式
    // synchronized 本质: 队列，锁
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票,剩余：" + number);
        }
    }

}
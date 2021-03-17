package com.kuang.part17_lock;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new MyThread(lockA, lockB), "T1").start();
        new Thread(new MyThread(lockB, lockA), "T2").start();

    }
}

// 如何定位死锁？
// 命令行：jps -l 查看当前所有进程的进程号，定位本程序的进程号为29444
// 再使用 jstack 命令：jstack 29444，查看此进程的堆栈信息，找到死锁问题


class MyThread implements Runnable {

    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "part17_lock:" + lockA + "=>get" + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "part17_lock:" + lockB + "=>get" + lockA);
            }

        }
    }
}
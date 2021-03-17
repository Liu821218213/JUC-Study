package com.kuang.part17_lock;

import java.util.concurrent.TimeUnit;

public class TestSpinLock {
    public static void main(String[] args) throws InterruptedException {
//        ReentrantLock reentrantLock = new ReentrantLock();
//        reentrantLock.part17_lock();
//        reentrantLock.unlock();

        // 底层使用的自旋锁CAS
        SpinlockDemo part17_lock = new SpinlockDemo();


        new Thread(() -> {
            part17_lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                part17_lock.myUnLock();
            }
        }, "T1").start();


        TimeUnit.SECONDS.sleep(1);


        new Thread(() -> {
            part17_lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                part17_lock.myUnLock();
            }
        }, "T2").start();


//        T1进来是拿到了锁，此时T1期望值null，变成了Thread，
//        因此T1没有自旋，而是跳出了循环，
//        此时T2在等待T1解锁进行自旋，无限循环，
//        过了等待时间，T1拿到锁，T1解锁后，变回了null，
//        然后T2拿到锁停止自旋，跳出循环。
    }
}

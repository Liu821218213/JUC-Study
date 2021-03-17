package com.kuang.part14_volatile;

import java.util.concurrent.atomic.AtomicInteger;

// volatile 不保证原子性
public class VDemo02 {

    // volatile 不保证原子性
    // 原子类的 Integer
//    private volatile static int num = 0;
    private volatile static AtomicInteger num = new AtomicInteger();

// javap -c VDemo02 反编译看到字节码文件：
//    public static void add();
//    Code:
//        0: getstatic     #2                  // Field num:I
//        3: iconst_1
//        4: iadd
//        5: putstatic     #2                  // Field num:I
//        8: return


//    public static void add();
//    Code:
//            0: getstatic     #2                  // Field num:Ljava/util/concurrent/atomic/AtomicInteger;
//            3: invokevirtual #3                  // Method java/util/concurrent/atomic/AtomicInteger.getAndIncrement:()I
//            6: pop
//       7: return


    public static void add() {
//         num++; // 不是一个原子性操作
        num.getAndIncrement(); // AtomicInteger + 1 方法， CAS
    }

    public static void main(String[] args) {

        //理论上num结果应该为 2 万
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) { // main  gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);


    }
}

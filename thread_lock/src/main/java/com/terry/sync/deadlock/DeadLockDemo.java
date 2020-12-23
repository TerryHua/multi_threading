package com.terry.sync.deadlock;

/**
 * 死锁例子
 */
public class DeadLockDemo implements Runnable {


    public int flag;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public void run() {
        System.out.println("线程" + Thread.currentThread().getName() + "的flag为" + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("线程1获得了两把锁");
                }
            }
        }
        if (flag == 2) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("线程2获得了两把锁");
                }
            }
        }
    }

    public static void main(String[] argv) {
        DeadLockDemo r1 = new DeadLockDemo();
        DeadLockDemo r2 = new DeadLockDemo();
        r1.flag = 1;
        r2.flag = 2;
        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");
        t1.start();
        t2.start();
    }


}

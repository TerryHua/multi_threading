package com.terry.sync.reenterlock;

import java.util.concurrent.locks.ReentrantLock;



/**
 * 重入锁 ReenterLock 一个线程允许连续获得同一把锁，注意：必须释放相同次数，释放次数多，会异常，少了相当于线程还持有这个锁，其他线程无法进入临界区
 * 需要手动指定何时加锁何时释放锁
 *
 * ReenterLock几个重要方法：
 * - lock():获得锁，如果锁已经被占用，则等待
 * - lockInterruptibly():获得锁，但优先响应中断
 * - tryLock():尝试获得锁，成功返回true，失败返回false，该方法不等待，立即返回
 * - tryLock(long time,TimeUnit unit):在给定时间内尝试获得锁
 * - unlock():释放锁
 */
public class ReentrantLockDemo implements Runnable {

    private static Integer i = 0;

    private ReentrantLock reentrantLock = new ReentrantLock();


    public void run() {
        for (int j = 0; j < 10000000; j++) {
            reentrantLock.lock();
            try {
                i++;
            } finally {
                reentrantLock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo r = new ReentrantLockDemo();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);  //20000000
    }
}

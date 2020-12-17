package com.terry.sync.reenterlock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 * synchronized产生的锁是非公平锁
 * 重入锁可以设置公平性
 */
public class FairLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock(true);

    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FairLock lock = new FairLock();
        Thread t1 = new Thread(lock, "thread_t1");
        Thread t2 = new Thread(lock, "thread_t2");
        t1.start();
        t2.start();
    }
    //...
    //thread_t2获得锁
    //thread_t1获得锁
    //thread_t2获得锁
    //thread_t1获得锁
    //thread_t2获得锁
    //...
    //两个线程基本交替获得锁

    //而非公平锁，一个线程会倾向于再次获得已持有的锁，这种分配方式是高效的，但无公平性可言
}
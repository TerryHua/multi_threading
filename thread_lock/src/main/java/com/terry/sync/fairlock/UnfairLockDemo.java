package com.terry.sync.fairlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 非公平锁：表示获取锁的抢占机制，是随机获取锁的，和公平锁不一样的就是先来的不一定能拿到锁，
 * 有可能一直拿不到锁，所以结果不公平。
 */
public class UnfairLockDemo {


    private ReentrantLock lock;

    public UnfairLockDemo(boolean isFair) {
        super();
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName()
                    + " 获取锁定");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final UnfairLockDemo service = new UnfairLockDemo(false);
        Runnable runnable = new Runnable() {

                
            public void run() {
                System.out.println("★线程" + Thread.currentThread().getName()
                        + "运行了");
                service.serviceMethod();
            }
        };
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
    }

}

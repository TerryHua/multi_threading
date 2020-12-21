package com.terry.sync.fairlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁：表示线程获取锁的顺序是按照加锁的顺序来分配的，及先来先得，先进先出的顺序。
 */
public class FairLockDemo {


    private ReentrantLock lock;

    public FairLockDemo(boolean isFair) {
        super();
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName()
                    + "获取锁定");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final FairLockDemo service = new FairLockDemo(true);
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

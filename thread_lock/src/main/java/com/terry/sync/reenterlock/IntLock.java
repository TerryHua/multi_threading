package com.terry.sync.reenterlock;

import java.util.concurrent.locks.ReentrantLock;


/**
 * 中断处理
 * <p>
 * 避免死锁
 * 重入锁的中断处理能力
 */
public class IntLock implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public IntLock(int lock) {
        this.lock = lock;
    }

    public void run() {
        try {
            if (lock == 1) {
                System.out.println("lock 1 start");
                lock1.lockInterruptibly();
                Thread.sleep(1000);
                lock2.lockInterruptibly();
                System.out.println("lock 1 end");

            } else {
                System.out.println("lock 2 start");
                lock2.lockInterruptibly();
                Thread.sleep(1000);
                lock1.lockInterruptibly();
                System.out.println("lock 2 end");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ":线程退出");

        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock i1 = new IntLock(1);
        IntLock i2 = new IntLock(2);
        Thread t1 = new Thread(i1);
        Thread t2 = new Thread(i2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt(); //中断t2 ，t2放弃对lock1的申请，同时释放已获得的lock2
        //java.lang.InterruptedException
        //	at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireInterruptibly(AbstractQueuedSynchronizer.java:898)
        //	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireInterruptibly(AbstractQueuedSynchronizer.java:1222)
        //	at java.util.concurrent.locks.ReentrantLock.lockInterruptibly(ReentrantLock.java:335)
        //	at com.combat.IntLock.run(IntLock.java:22)
        //	at java.lang.Thread.run(Thread.java:748)
        //10:线程退出
        //9:线程退出
    }

}

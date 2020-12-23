package com.terry.sync.spinlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName SpinLockDemo
 * @Description 自旋锁
 * <p>
 * 自旋锁是指尝试获取锁的线程不会立即堵塞，而是采用循环的方式尝试去获取锁
 * 这样的好处是可以减少线程上下文的切换， 缺点是循环会消耗cpu
 **/
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t come in !");
        while (!atomicReference.compareAndSet(null, thread)) {
//            System.out.println("我转！我转！");
        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "\t myUnLock !");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo demo = new SpinLockDemo();
        new Thread(() -> {
            demo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.myUnLock();
        }, "AA").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            demo.myLock();
            demo.myUnLock();
        }, "BB").start();
    }
}
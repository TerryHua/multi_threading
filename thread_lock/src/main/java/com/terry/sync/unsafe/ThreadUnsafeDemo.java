package com.terry.sync.unsafe;

/**
 * 线程安全，两个线程同时操作count，多次操作得到的结果不一样
 */

public class ThreadUnsafeDemo {


    public static void main(String[] args) {
        CountObject countObject = new CountObject();
        Thread thread1 = new Thread(countObject);
        Thread thread2 = new Thread(countObject);

        thread1.start();
        thread2.start();
    }


}

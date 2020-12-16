package com.terry.sync.area;

public class SynchronizedNormalMethodDemo {

    public synchronized void method1() {
        System.out.println("method 1 start");
        try {
            System.out.println("method 1 exec");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method 1 end");
    }


    public synchronized void method2() {
        System.out.println("method 2 start");
        try {
            System.out.println("method 2 exec");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedNormalMethodDemo syncronizedNormalMethodDemo1 = new SynchronizedNormalMethodDemo();
        final SynchronizedNormalMethodDemo syncronizedNormalMethodDemo2 = new SynchronizedNormalMethodDemo();

        new Thread(new Runnable() {
            public void run() {
                syncronizedNormalMethodDemo1.method1();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                syncronizedNormalMethodDemo2.method2();
            }
        }).start();
    }
}

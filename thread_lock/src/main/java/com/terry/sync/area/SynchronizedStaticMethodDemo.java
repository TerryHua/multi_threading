package com.terry.sync.area;


/**
 *
 * 这样即使我们调用的是不同对象的method1方法和method2方法，但是它们获取的锁是类对象的锁(Class对象的锁)，
 * 因此，不论使用的是哪个对象的method1和method2，均会发生线程阻塞的情况。
 * 只有当一个线程中的静态synchronized执行完成后才会执行另一个线程中的静态synchronized方法。
 *
 * 当synchronized作用在实例方法时，监视器锁（monitor）便是对象实例（this）；
 * 当synchronized作用在静态方法时，监视器锁（monitor）便是对象的Class实例，因为Class数据存在于永久代，因此静态方法锁相当于该类的一个全局锁；
 * 当synchronized作用在某一个对象实例时，监视器锁（monitor）便是括号括起来的对象实例；
 *
 */
public class SynchronizedStaticMethodDemo {

    public static synchronized void method1() {
        System.out.println("method 1 start");
        try {
            System.out.println("method 1 exec");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method 1 end");
    }


    public static synchronized void method2() {
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
        final SynchronizedStaticMethodDemo synchronizedStaticMethodDemo1 = new SynchronizedStaticMethodDemo();
        final SynchronizedStaticMethodDemo synchronizedStaticMethodDemo2 = new SynchronizedStaticMethodDemo();

        new Thread(new Runnable() {
            public void run() {
                synchronizedStaticMethodDemo1.method1();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                synchronizedStaticMethodDemo2.method2();
            }
        }).start();

    }
}

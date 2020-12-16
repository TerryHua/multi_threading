package com.terry.sync.area;


/**
 * 当synchronized被使用在代码块上时，它所获取的锁是括号中接收的对象的锁(如样例代码中的this指代的是当前调用它的对象)，
 * 这样，假设method1方法正在执行，并且进入代码块休眠3秒，
 * 这时method2获取cpu，开始执行第一行代码打印"method2 start"，然后遇见代码块，尝试获取this对象的锁，
 * 却发现已经被method1获取(sleet不释放锁)，因此method2进入阻塞状态，直到method1执行完毕后释放锁，method2获取锁开始执行代码块。
 */
public class SynchronizedCodeBlockDemo {

    public void method1() {
        System.out.println("method 1 start");

        synchronized (this) {
            try {
                System.out.println("method 1 exec");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("method 1 end");
    }


    public void method2() {
        System.out.println("method 2 start");

        synchronized (this) {
            try {
                System.out.println("method 2 exec");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedCodeBlockDemo synchronizedCodeBlockDemo = new SynchronizedCodeBlockDemo();
        new Thread(new Runnable() {
            public void run() {
                synchronizedCodeBlockDemo.method1();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                synchronizedCodeBlockDemo.method2();
            }
        }).start();



    }
}

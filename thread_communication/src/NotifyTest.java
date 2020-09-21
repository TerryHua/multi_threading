/**
 * 有了对wait方法原理的理解，notify方法和notifyAll方法就很容易理解了。既然wait方式是通过对象的monitor对象来实现的，
 * 所以只要在同一对象上去调用notify/notifyAll方法，就可以唤醒对应对象monitor上等待的线程了。
 * notify和notifyAll的区别在于前者只能唤醒monitor上的一个线程，对其他线程没有影响，而notifyAll则唤醒所有的线程
 *
 * @since 2020-09-21 14:50:17
 */
public class NotifyTest {

    public synchronized void testNotify() {
        System.out.println(Thread.currentThread().getName() +  " start-------------" + System.currentTimeMillis());
        try {
            wait(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end------------" + System.currentTimeMillis());
    }


    public static void main(String[] args) throws InterruptedException{
        NotifyTest notifyTest = new NotifyTest();
        for (int i=0; i<5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    notifyTest.testNotify();
                }
            }).start();
        }

        synchronized (notifyTest) {
            notifyTest.notify();
        }

        Thread.sleep(3000);
        System.out.println("------------分割线-----------------");

        synchronized (notifyTest) {
            notifyTest.notifyAll();
        }

    }

}

/**
 * 通过sleep方法实现的暂停，程序是顺序进入同步块的，只有当上一个线程执行完成的时候，
 * 下一个线程才能进入同步方法，sleep暂停期间一直持有monitor对象锁，其他线程是不能进入的。
 * 而wait方法则不同，当调用wait方法后，当前线程会释放持有的monitor对象锁，
 * 因此，其他线程还可以进入到同步方法，线程被唤醒后，需要竞争锁，获取到锁之后再继续执行。
 *
 * @since 2020-09-21 15:15:35
 */

public class SleepTest {

    public synchronized void sleepMethod() {
        System.out.println("Sleep start ---------------");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sleep end ----------------");

    }


    public synchronized void waitMethod() {
        System.out.println("Wait start ---------------");
        synchronized (this) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Wait end ------------------");
    }

    public static void main(String[] args) {
        SleepTest sleepTest = new SleepTest();
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleepTest.sleepMethod();
                }
            }).start();
        }


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("----------------分割线------------------");

        SleepTest sleepTest1 = new SleepTest();

        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleepTest1.waitMethod();
                }
            }).start();
        }
    }
}

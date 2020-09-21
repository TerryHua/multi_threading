/**
 * 线程协作
 * wait()样例代码
 * wait方法是一个本地方法，其底层是通过一个叫做监视器锁的对象来完成的。所以上面之所以会抛出异常，
 * 是因为在调用wait方式时没有获取到monitor对象的所有权，那如何获取monitor对象所有权？Java中只能通过Synchronized关键字来完成
 * wait方法的使用必须在同步的范围内，否则就会抛出IllegalMonitorStateException异常，wait方法的作用就是阻塞当前线程等待notify/notifyAll方法的唤醒，或等待超时后自动唤醒。
 *
 * @since 2020-09-21 14:52:34
 *
 */
public class WaitTest {

    public synchronized void testWait() {

        System.out.println("Start----------" + System.currentTimeMillis());
        try {
            wait(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End----------" + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        WaitTest test = new WaitTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.testWait();
            }
        }).start();
    }

}

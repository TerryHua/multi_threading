/**
 *　　yield方法的作用是暂停当前线程，以便其他线程有机会执行，
 * 不过不能指定暂停的时间，并且也不能保证当前线程马上停止。
 * yield方法只是将Running状态转变为Runnable状态。：
 *
 * 调度器可能会忽略该方法。
 * 使用的时候要仔细分析和测试，确保能达到预期的效果。
 *　很少有场景要用到该方法，主要使用的地方是调试和测试。　
 */
public class YieldTest implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0;i<5; i++) {
            System.out.println(Thread.currentThread().getName() + " ------- " + i);
            Thread.yield();
        }
    }


    public static void main(String[] args) {
        YieldTest yieldTest = new YieldTest();
        Thread t1 = new Thread(yieldTest, "FirstThread");
        Thread t2 = new Thread(yieldTest, "secondThread");

        t1.start();
        t2.start();
    }

}

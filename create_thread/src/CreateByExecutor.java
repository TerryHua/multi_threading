import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 通过线程池创建线程
 */
public class CreateByExecutor implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ˙执行时间：" + System.currentTimeMillis());

    }

    public static void main(String[] args) {

        //创建使用单个线程的线程池
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            es1.submit(new CreateByExecutor());

        }

        //创建使用固定线程数的线程池
        ExecutorService es2 = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            es2.submit(new CreateByExecutor());
        }

        //创建一个会根据需要创建新线程的线程池
        ExecutorService es3 = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            es3.submit(new CreateByExecutor());
        }

        //创建拥有固定线程数量的定时线程任务的线程池
        ScheduledExecutorService es4 = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 5; i++) {
            es4.schedule(new CreateByExecutor(), 10, TimeUnit.SECONDS);
        }

        //创建只有一个线程的定时线程任务的线程池
        ScheduledExecutorService es5 = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 5; i++) {
            es5.schedule(new CreateByExecutor(), 20, TimeUnit.SECONDS);
        }


    }
}

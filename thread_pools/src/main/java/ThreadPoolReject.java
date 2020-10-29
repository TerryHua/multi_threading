import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadPoolReject {


    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("线程名称-%s").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor( 10, 100, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), threadFactory, new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.execute(new ThreadPoolFactory.Task());
        }
//        System.out.println(Thread.currentThread().getName());
    }


    static class Task implements Runnable {

        public void run() {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }
}

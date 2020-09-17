import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 通过Callable创建线程
 */
public class CreateByCallable implements Callable<String> {

    @Override
    public String call(){

        for (int i=0; i<10; i++) {
            System.out.println(Thread.currentThread().getName() + " ˙执行时间：" + System.currentTimeMillis() + "执行次数是：" + i );
        }
        return "1111";

    }

    public static void main(String[] args) {
        FutureTask<String> task  = new FutureTask<String>(new CreateByCallable());
        Thread thread = new Thread(task);
        thread.run();

    }
}

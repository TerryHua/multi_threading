/**
 * 通过实现Runnable接口创建线程
 *
 */
public class CreateByRunnable implements Runnable {

    @Override
    public void run() {
        for (int i= 0; i<10; i++ ) {
            System.out.println(Thread.currentThread().getName() + " ˙执行时间：" + System.currentTimeMillis() + "执行次数是：" + i );

        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new CreateByRunnable());
        thread.start();
    }


}

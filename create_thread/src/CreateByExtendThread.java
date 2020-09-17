/**
 * 通过继承Thread创建线程
 *
 */
public class CreateByExtendThread extends Thread {

    public void run() {
        for (int i=0; i<10; i++) {
            System.out.println(Thread.currentThread().getName() + " ˙执行时间：" + System.currentTimeMillis() + "执行次数是：" + i );
        }
    }

    public static void main(String[] args) {
        CreateByExtendThread createThreadByExtends = new CreateByExtendThread();
        createThreadByExtends.start();
    }
}

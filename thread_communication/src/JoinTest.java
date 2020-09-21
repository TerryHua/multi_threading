/**
 * join测试对比案例，不使用join，看运行结果。finished可能会在其他start，end先执行完成
 */
public class JoinTest implements Runnable{

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + "start ---------------");

            Thread.sleep(1000);

            System.out.println(Thread.currentThread().getName() + "end ---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        for (int i=0; i<5; i++) {
            Thread test = new Thread(new JoinTest());
            test.start();
        }
        System.out.println("finished -----------");
    }

}

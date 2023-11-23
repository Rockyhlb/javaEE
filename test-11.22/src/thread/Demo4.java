package thread;

/**
 * @BelongsProject: test-11.22
 * @BelongsPackage: thread
 * @CreateTime : 2023-11-23 16:17
 * @Description: 4、实现 Runnable 接口,通过匿名内部类重写 run方法来创建线程
 * @Author: code_hlb
 */
public class Demo4 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("hello thread");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread4.start();
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}

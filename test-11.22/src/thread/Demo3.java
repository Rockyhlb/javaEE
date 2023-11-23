package thread;

/**
 * @BelongsProject: test-11.22
 * @BelongsPackage: thread
 * @CreateTime : 2023-11-23 16:17
 * @Description: 3、继承 Thread,通过匿名内部类重写 run方法来创建线程
 * @Author: code_hlb
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        // 匿名内部类只运行一次
        Thread thread3 = new Thread(){
            @Override
            public void run() {
                while (true) {
                    System.out.println("my thread");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread3.start();
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}

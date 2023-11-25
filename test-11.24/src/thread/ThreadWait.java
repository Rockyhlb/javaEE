package thread;

/**
 * @BelongsProject: test-11.24
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/24 15:18
 * @Description: 线程等待
 * @Author: code_hlb
 */
public class ThreadWait {
    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        // 线程休眠时间不一定就是准确的1000ms,因为sleep本身存在一定的精度误差，
        // 这是由于1000ms时间到了以后，系统将线程从 阻塞->就绪->执行 唤醒,这中间存在调度开销，因此会花更多时间
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("休眠时间：" + (end - begin) + " ms");
    }

    public static void main1(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
        // 让主线程等待 t线程执行结束，一旦调用join，主线程就会发生阻塞，此时t线程就会往后继续执行
        // 一直阻塞到 t线程执行完毕，join才会解除阻塞继续向后执行
        System.out.println("开始阻塞...");
        // join()默认是“死等”，一般不建议，因此join(long millis)提供了“超时等待”
        t.join(1000);
        System.out.println("阻塞结束...");
    }
}

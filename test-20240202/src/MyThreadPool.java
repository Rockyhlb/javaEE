
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @BelongsProject: test-20240202
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/2 22:04
 * @Description: 自定义线程池
 * @Author: code_hlb
 */
public class MyThreadPool {
    // 1、设置一个阻塞队列，这里我们采用
    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);
    // 2、提供一个公开方法用于提交任务
    public void submit(Runnable runnable) throws InterruptedException {
        // put/take
        queue.put(runnable);
    }
    // 3、定义构造方法,通过传参控制线程池数目
    public MyThreadPool(int num) {
        // 创建num个线程执行阻塞队列中的任务
        for (int i = 0; i < num; i++) {
            Thread thread = new Thread(() -> {
                // 消费阻塞队列中的任务
                try {
                    Runnable runnable = queue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }
}

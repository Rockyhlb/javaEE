package thread;

/**
 * @BelongsProject: test-11.24
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/24 15:50
 * @Description: 线程状态
 * @Author: code_hlb
 */
public class ThreadState {
    /**
     * 线程状态:
     * 1、NEW : Thread 对象已经有了，start 方法还没调用
     * 2、TERMINATED: Thread对象还在，内核中的线程已经没了
     * 3、RUNNABLE: 就绪态(线程已经在CPU上执行/线程正在排队等待CPU执行)
     * 4、TIMED_WAITING: 阻塞态，由于sleep这种固定时间方式产生的阻塞
     * 5、WAITING: 阻塞态，由于wait这种不固定时间的方式而产生的阻塞
     * 6、BLOCKED: 阻塞态，由于锁竞争导致的阻塞
     * 三种阻塞态的作用: 当后续出现”线程卡死“时，就很容易通过状态初步确定卡死的原因
     * */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
        for (int i = 0; i < 5; i++) {
            // 当没有sleep时，线程是RUNNABLE态，当加入sleep以后，第一次还是RUNNABLE态，接着遇见sleep变成TIMED_WAITING态
            System.out.println(t.getState());
            Thread.sleep(1000);
        }
    }

    public static void main1(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {});
        // 此时线程还未开始，因此还是 NEW态
        System.out.println("before start: " + t.getState());
        t.start();
        // 等线程t 运行结束
        t.join();
        // 此时线程执行结束，因此是 TERMINATED态
        System.out.println("after start: " + t.getState());
    }
}

package thread;

/**
 * @BelongsProject: test-11.24
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/24 16:34
 * @Description: 线程安全
 * @Author: code_hlb
 */
public class ThreadSecure {
    /**
     * 线程安全问题(线程中最复杂最重要的部分)：指同一个代码，在单线程中能够输出正确结果，但当在多线程中运行时就会出现bug
     * 产生 线程安全问题 的原因：
     * 1、操作系统中，线程的调度顺序是随机的(抢占式执行)
     * 2、两个线程，针对 同一个变量 进行 修改
     * 3、代码中的 修改操作 并不是 原子操作(系统只需只需一步操作,下面的count++就不是原子操作)
     * 4、内存可见性问题
     * 5、指令重排序问题
     */
    public static int count;
    public static void main(String[] args) throws InterruptedException {
        // locker 表示加锁的对象，用来区分两个线程是否在竞争同一个锁
        // 锁对象是谁不重要，重要的是需要加锁的两个线程中的 锁对象 是否是同一个对象
        Object locker = new Object();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                // 通过更改为原子操作的方式来解决线程安全问题
                // 通过 加锁 让count++的三步走 成为原子操作  本质上是把 "并发执行" => "串行执行"
                // synchronized 要搭配{}使用，进入‘{’就会加锁，出了‘}’就会解锁，在已经加锁的状态中，另一个线程同样尝试加锁，
                // 就会产生”锁冲突/锁竞争“，后一个进程就会阻塞等待，一直等到上一个进程解锁为止。（此时后一个进程就处于 "BLOCKED"状态）
                synchronized (locker) {
                    count++;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized(locker) {
                    count++;
                }
            }
        });
        t1.start();
        // 将 t1.join() 挪到这里可以正常输出2w了，但是两个线程就不是”并发执行“了
        t2.start();

        // 防止主线程跑在子线程前面(还没count++,先打印出count)
        t1.join();
        t2.join();
        // 系统执行count++程序经历了三步，分别是 从寄存器中load,add,save到内存中
        // 预期输出结果应该是2w,输出结果却不确定，这是由于线程之间的调度顺序是 随机 的，因此就会导致两个线程的 load、add、save 出现穿插
        System.out.println("count: " + count);

    }
}

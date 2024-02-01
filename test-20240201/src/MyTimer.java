
import java.util.PriorityQueue;

/**
 * @BelongsProject: test-20240201
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/1 21:42
 * @Description: TODO
 * @Author: code_hlb
 */
public class MyTimer {
    // 2、优先级队列组织定时器任务
    private final PriorityQueue<MyTimerTask> queue = new PriorityQueue<>();
    private final Object locker = new Object();
    // 2.1 插入定时器任务
    public void schedule(Runnable runnable,long delay) {
        // 多个线程对同一个队列进行写操作，对其加锁保证线程安全性
        synchronized (locker) {
            queue.offer(new MyTimerTask(runnable,delay));
            // 队列非空，唤醒线程
            locker.notify();
        }
    }

    public MyTimer() {
        // 3、扫描线程时刻扫描定时器任务
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    // 对队列进行读操作，仍需要对其加锁
                    synchronized (locker) {
                        // 避免使用“if”判断线程是否需要等待，因为线程不仅可以被notify唤醒，也可能是被interruptException唤醒
                        // 因此wait一般搭配while循环使用，相当于加了双重保险
                        // if (queue.isEmpty()) {
                        while (queue.isEmpty()) {
                            // 当队列为空时，让其等待
                            // wait 进行的操作：1、释放当前锁  2、等待通知(notify)  3、通知到来之后唤醒线程，重新获取锁
                            locker.wait();
                        }
                        long currentTime = System.currentTimeMillis();
                        MyTimerTask task = queue.peek();
                        if (currentTime >= task.getDelay()) {
                            // 取出队首定时器任务并执行
                            queue.poll().getRunnable().run();
                        }else {
                            System.out.println("还需等待: " + (task.getDelay() - currentTime) + "ms");
                            // 精准定位
                            locker.wait(task.getDelay()-currentTime);
                        }
                    }
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
}

// 1、定义一个类描述定时器任务
// 又因为对于优先级队列(PriorityQueue/TreeSet/TreeMap)来说，里面的元素务必是可以比较的，因此需要实现Comparable接口
//  Comparable.compareTo  需要手动实现接口，对类的侵入性比较强，但一旦实现，每次用该类都有顺序，属于内部顺序
//  Comparator.compare    需要实现一个比较器对象，对待比较类的侵入性弱，但对算法代码实现侵入性较强
class MyTimerTask implements Comparable<MyTimerTask>{
    private final Runnable runnable;
    private final long delay;
    public MyTimerTask(Runnable runnable,long delay) {
        this.runnable = runnable;
        // 采取绝对时间定时方便比较
        this.delay = System.currentTimeMillis() + delay;
    }
    public Runnable getRunnable() {
        return runnable;
    }
    public long getDelay() {
        return delay;
    }
    @Override
    public int compareTo(MyTimerTask o) {
        return (int) (this.delay - o.delay);
    }
}

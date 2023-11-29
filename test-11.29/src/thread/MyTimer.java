package thread;

import java.util.PriorityQueue;

/**
 * @BelongsProject: test-11.29
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/29 8:36
 * @Description: 自定义定时器
 * @Author: code_hlb
 */
public class MyTimer {
    // 创建一个锁对象
    private final Object locker = new Object();
    // 2、通过数据结构-->优先级队列 存储任务(实现阻塞队列的效果)
    PriorityQueue<MyTimerTask> queue = new PriorityQueue<>();
    // 存储任务
    public void schedule(Runnable runnable,long delay) {
        synchronized (locker) {
            queue.offer(new MyTimerTask(runnable, delay));
            // 线程唤醒
            locker.notify();
        }
    }
    //3、构造扫描线程，应该伴随着类对象的构建而启动,因此应该位于构造器中，并且要处于一直扫描的状态，因此是死循环
    public MyTimer() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    synchronized (locker) {
                        while (queue.isEmpty()) {
                            // 线程等待
                            locker.wait();
                        }
                        MyTimerTask task = queue.peek();
                        long curTime = System.currentTimeMillis();
                        if (curTime >= task.getTime()) {
                            // 到达指定时间
                            task.getRunnable().run();
                            queue.poll();
                        } else {
                            // 未到达指定时间
                            // 直接等待到任务开始时间，防止一直忙等
                            locker.wait(task.getTime() - curTime);
                        }
                    }
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
}

// 1、建立一个类描述任务
class MyTimerTask implements Comparable<MyTimerTask>{
    private final Runnable runnable;
    private final long time;

    public MyTimerTask(Runnable runnable, long time) {
        this.runnable = runnable;
        // 将任务开始时间设置为 绝对时间
        this.time = System.currentTimeMillis() + time;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public long getTime() {
        return time;
    }

    @Override
    public int compareTo(MyTimerTask o) {
        return (int)(this.time - o.time);
    }
}

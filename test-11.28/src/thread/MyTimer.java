package thread;

import java.util.PriorityQueue;

/**
 * @BelongsProject: test-11.28
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/28 16:04
 * @Description: 自定义定时器
 * @Author: code_hlb
 */
public class MyTimer {
    // 定义一个锁对象，保证线程安全性
    private final Object locker = new Object();
    // 2、采用优先级队列存储任务
    private final PriorityQueue<MyTimerTask> queue = new PriorityQueue<>();
    public void schedule(Runnable runnable,long delay) {
        // 将任务插入到队列中
        synchronized(locker) {
            queue.offer(new MyTimerTask(runnable,delay));
            // 队列非空，唤醒线程
            locker.notify();
        }
    }
    public MyTimer() {
        // 3、扫描线程，需要一直扫描队首元素，看是否到达任务开始时间
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    synchronized (locker) {
                        // 使用while唤醒的目的是为了在 wait 被唤醒的时候再进行一次确认，防止是被interrupt异常唤醒的
                        while (queue.isEmpty()) {
                            // 优先级队列中还没有任务，线程应该等待到队列中插入任务，类似于阻塞队列
                            // 此处实现的定时器用PriorityQueue，不用PriorityBlockingQueue，就是因为要处理此处
                            // 和下面共两处的 wait，使用线程安全的优先级队列不容易实现。
                            locker.wait();
                        }
                        long curTime = System.currentTimeMillis();
                        MyTimerTask task = queue.peek();
                        if (curTime >= task.getTime()) {
                            // 到达时间时执行任务
                            task.getRunnable().run();
                            queue.poll();
                        } else {
                            // 当前时间还没到任务时间，暂时不执行任务
                            System.out.println("current time: " + curTime);
                            // 防止 “忙等”，例如当定时器时间为3000ms,则从当前时间开始就会一直进入循环判定，
                            // 从而消耗大量cpu资源
                            // 以下代码就可以通过等待 task.getTime() - curTime，直接定位到任务开始的时间
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

// 1、定义一个类来存储定时器实现的任务对象
class MyTimerTask implements Comparable<MyTimerTask>{
    private final Runnable runnable; // 要执行的任务
    private final long time;  // 要执行的时间
    public MyTimerTask(Runnable runnable, long delay) {
        this.runnable = runnable;
        // 记录任务开始的绝对时间
        this.time = System.currentTimeMillis() + delay;
    }
    public Runnable getRunnable() {
        return runnable;
    }
    public long getTime() {
        return time;
    }
    @Override
    public int compareTo(MyTimerTask o) {
        return (int) (this.time - o.time);
    }
}

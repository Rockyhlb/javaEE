package Thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: test-20240203
 * @BelongsPackage: Thread
 * @CreateTime : 2024/2/3 20:34
 * @Description: Thread.JUC(java.util.concurrent:这个包里都是和并发编程的相关组件)的常见类
 * @Author: code_hlb
 */
public class JUC {
    public static void main1(String[] args) throws ExecutionException, InterruptedException {
        // Callable接口: 也是一种创建线程的方式,适用于想让线程执行某段逻辑，并返回结果的时候
        // 定义任务
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i < 1000; i++) {
                    sum++;
                    // 当
                    // Thread.sleep(5000);
                }
                return sum;
            }
        };
        // 把任务放到线程当中执行
        // futureTask相当于一张“小票”，凭借这张小票可以取出线程的执行结果
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        // 此处的get()方法就可以取出callable的返回结果
        System.out.println(futureTask.get());

        // 线程的创建方式：
        // 1、继承Thread，重写run方法(创建单独的类 或 匿名内部类)
        // 2、实现Runnable,重写run方法(创建单独的类 或 匿名内部类)
        // 3、lambda 表达式
        // 4、实现Callable接口(创建单独的类 或 匿名内部类)
        // 5、线程池
        // 6、ThreadFactory线程工厂
        // ...
    }

    public static void main2(String[] args) {
        // ReentrantLock: 也是可重入锁，效果和synchronized类似
        // 优势：1、提供了 lock() 和 tryLock() 两种加锁方式，后者只是尝试加锁，因此提供了更多的可操作空间
        //      2、提供了公平锁(先来先服务)的实现(默认也是非公平锁)
        //      3、提供了更强大的等待通知机制，搭配了Condition类实现等待通知
        // 但是我们平时在加锁的时候，还是首选synchronized，因为synchronized使用简单，并且还有很多的优化手段
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            // reentrantLock.tryLock();
            reentrantLock.lock();
        }finally {
            // 不要忘记解锁！！
            reentrantLock.unlock();
        }
    }

    public static void main3(String[] args) throws InterruptedException {
        // Semaphore(信号量)：本质是是一个计数器，标记了可用资源的数量，
        // 每次申请资源，则信号量-1 (P操作：acquire)
        // 每次释放资源，则信号量+1 (V操作：release)
        // 锁，本质上就是一种可用资源为1的信号量(又称二元信号量，因为只有0和1的变化)
        Semaphore semaphore = new Semaphore(3);
        semaphore.acquire();
        System.out.println("P操作：semaphore.acquire()");
        semaphore.acquire();
        System.out.println("P操作：semaphore.acquire()");
        semaphore.acquire();
        System.out.println("P操作：semaphore.acquire()");
        // 此时信号量已经被占用完，所以进入阻塞等待
        semaphore.acquire();
        System.out.println("P操作：semaphore.acquire()");
        // V操作
        // semaphore.release();
    }

    public static void main4(String[] args) throws InterruptedException {
        // CountDownLatch: 当我们将一个任务分解成多线程小任务完成时，我们需要判定是否每一个子线程的任务都已经完成，就可以使用CountDownLatch
        // 类似于IDM多线程下载方式，只有每一个线程下载完毕之后，我们这个下载任务才能说结束
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            // 变量捕获
            int temp = i;
            Thread thread = new Thread(() -> {
                System.out.println("thread: " + temp + " working...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 告知当前子线程已经执行完毕
                countDownLatch.countDown();
            });
            thread.start();
        }
        // 调用await()的时候就会阻塞，只有等到所有的子线程任务完成以后才会继续向下执行
        countDownLatch.await();
        System.out.println("所有任务执行完毕~");
    }
}

package thread;

/**
 * @BelongsProject: test-11.22
 * @BelongsPackage: thread
 * @CreateTime : 2023-11-22 16:16
 * @Description: 1、继承 Thread，重写 run方法来创建线程
 * @Author: code_hlb
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程，两个主要操作就是：1、明确线程要执行的任务   2、调用系统 API 创建线程
        Thread t = new MyThread1();
        // start 和 run 都是Thread 的成员
        // run 只是描述了线程的入口(线程需要做什么任务)
        // start 则是真正调用了系统API,在系统中创建出线程，让线程再调用 run;
        t.start();
        // 调用 t.run 不会创建线程，因此只会在主线程中逐句运行，就会陷入 hello thread 循环
        // t.run();
        // 再开一个线程，分路执行
        while(true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}

class MyThread1 extends Thread{
    // Thread 在java.lang 这个包下，因此此处没有 import 导包
    @Override
    public void run() {
        // run就是线程的入口方法
        while (true) {
            System.out.println("hello thread");
            try {
                // 因为这是重写run方法，而父类的 run 方法没有throws关键字，因此只能用try-catch 捕捉受查异常
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package thread;

/**
 * @BelongsProject: test-11.22
 * @BelongsPackage: thread
 * @CreateTime : 2023-11-23 16:10
 * @Description: 2、实现 Runnable, 重写 run方法来创建线程
 * @Author: code_hlb
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        // Runnable 表示的是一个“可执行的任务”
        Runnable runnable = new MyThread2();
        Thread thread2 = new Thread(runnable);

        thread2.start();
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}

// 通过使用 runnable 的写法，和直接继承 Thread 之间的区别主要就是为了解耦合
class MyThread2 implements Runnable {
    @Override
    public void run() {
        while(true) {
            System.out.println("hello thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

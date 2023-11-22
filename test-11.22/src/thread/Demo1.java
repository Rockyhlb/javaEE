package thread;

/**
 * @BelongsProject: test-11.22
 * @BelongsPackage: thread
 * @CreateTime : 2023-11-22 16:16
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    public static void main(String[] args) {
        Thread t = new MyThread();
        // start 和 run 都是Thread 的成员
        // run 只是描述了线程的入口(线程需要做什么任务)
        // start 则是真正调用了系统API,在系统中创建出线程，让线程再调用 run;
        t.start();
    }
}

class MyThread extends Thread{
    // Thread 在java.lang 这个包下，因此此处没有 import 导包
    @Override
    public void run() {
        // run就是线程的入口方法
        System.out.println("hello thread");
    }
}

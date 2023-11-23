package thread;

/**
 * @BelongsProject: test-11.22
 * @BelongsPackage: thread
 * @CreateTime : 2023-11-23 21:15
 * @Description: Thread 的基本属性
 * @Author: code_hlb
 */
public class Demo6 {

    // 线程终止
    public static void main(String[] args) {
        Thread thread9 = new Thread(() -> {
            // !Thread.currentThread()获取当前线程对象的实例，isInterrupted 判断线程是否结束
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("thread is working~");
                try {
                    // 正常情况下，sleep只有到休眠时间时才会被唤醒，运行过程中给出的 interrupt 就可以使 sleep内部触发异常
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //1、假装没听见，循环继续执行
                    e.printStackTrace();
                    //2、加上一个break,让线程结束
                    break;
                    //3、做一些其它收尾工作，完成之后再结束线程
                    // 通过异常的方式唤醒更多的 “可操作性空间”，因此如果此处没有sleep,就没有后续操作
                }
            }
        });
        thread9.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread is interrupted");
        // 把thread对象内部的标志位设置为 true,即使线程内部的逻辑出现阻塞(sleep)，也是会被唤醒的
        // interrupt 唤醒进程之后，此时sleep方法抛出异常，同时会自动清除刚才设置的标志位，因此抛出异常以后还是会继续循环
        thread9.interrupt();
    }

    // 把isQuit改成成员变量,此时lambda访问这个成员,就不是变量捕获的语法了,而是“内部类访问外部类的属性”.此时就没有了final限制
    private static boolean isQuit = false;
    public static void main3(String[] args) throws InterruptedException {
        // 错误写法：boolean isQuit = false; 因为lambda 表达式变量捕获的只能是 final或者 类似 final的变量(没有对变量进行修改)
        // 中断(终止/打断)一个线程  -->  想办法让 run 尽快执行结束
        Thread thread8 = new Thread(() -> {
           while (!isQuit) {
               System.out.println("thread is working~");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           System.out.println("thread work over!");
        });
        thread8.start();
        Thread.sleep(5000);
        isQuit = true;
        System.out.println("isQuit is true");
    }

    /* 1、面试题：start 和 run 有什么区别：
    start 方法内部会调用到系统 API 在系统内核中创建线程
    run 方法只是单纯的描述了该线程要执行什么内容(会在start创建好线程之后自动被调用)
    * */
    public static void main2(String[] args) throws InterruptedException {
        Thread thread7 = new Thread(() -> {
            System.out.println("start thread");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end thread");
        });
        thread7.start();
        // Thread 对象的生命周期，要比系统内核中的线程更长一些，
        // 因此可能就会出现 Thread对象还在，但是内核中的线程已经销毁了的情况，
        // 因此就可以使用 isAlive 来判断内核线程是不是已经挂了(回调方法执行完毕，线程就没了)
        System.out.println(thread7.isAlive());
        Thread.sleep(3000);
        System.out.println(thread7.isAlive());
        // 最终结果打印：（true 和 start 这俩日志谁先打印不一定，因为线程是并发执行的， 并发执行顺序取决于系统的调度器）
        //true
        //start thread
        //end thread
        //false
    }

    public static void main1(String[] args) throws InterruptedException {
        // 构造方法：Thread(Runnable target, String name)   name 不影响线程的执行，只是给线程起了个名字，方便后续的调试
        Thread thread6 = new Thread(() -> {
            while (true) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"MyThread");
/*         Thread 的常见属性：
         1、ID: getId()
         2、名称: getName()
         3、状态: getState()
         4、优先级: getPriority()
         5、是否是后台进程: isDaemon()    JVM 会在一个进程的所有前台进程结束以后，才会结束运行
         6、是否存活: isAlive()   可以简单理解为 run() 是否运行结束了
         6、是否被中断: isInterrupted()*/
        System.out.println(thread6.getId());
        System.out.println(thread6.getName());
        System.out.println(thread6.getState());
        System.out.println(thread6.getPriority());
        // thread 默认为前台线程(如果前台线程没有执行结束，那么整个进程是不会结束的)，
        System.out.println(thread6.isDaemon());
        // 现在将它设置为后台线程(后台线程不结束并不会影响整个进程的结束)
        thread6.setDaemon(true);
        // 改成后台线程后，主线程(main)很快就执行完了,此时没有前台线程，因此进程结束
        thread6.start();
    }
}

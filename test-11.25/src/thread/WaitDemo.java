package thread;

/**
 * @BelongsProject: test-11.25
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/25 14:59
 * @Description: wait 和 notify
 * @Author: code_hlb
 */
public class WaitDemo {
    /**
     * 由于多个线程的执行顺序是随机的(系统随机调度，抢占式执行)，因此我们需要通过一些方法协调执行顺序
     * wait 和 notify: 多线程中比较重要的机制，主要用来协调多个线程的执行顺序 和 避免 ”线程饿死“(wait时不会占据cpu,且释放了锁，其它线程就有机会访问锁)
     * 1、wait(等待)，让指定线程 进入阻塞 状态, (join 是影响线程结束的先后顺序，此处是希望线程不结束，也能够先后顺序的控制)
     * 2、notify(通知)，唤醒对应的 阻塞状态 的线程
     * wait()、notify()、notifyAll() 都是Object类的方法
     */
    public static void main(String[] args) {
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (object) {
                System.out.println("before wait");
                try {
                    object.wait(5000);  // 指定超时时间
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("after wait");
            }
        });

        Thread t2 = new Thread(() ->  {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (object) {
                System.out.println("is notified");
                // 唤醒等待的线程，若有多个线程等待，则由线程调度器随机挑选出一个呈”waiting“态的线程
                object.notify();
            }
        });
        t1.start();
        t2.start();
    }

    public static void main1(String[] args) throws InterruptedException {
        Object object = new Object();
        object.wait();
        // 直接调用 object.wait() 时会出现 “IllegalMonitorStateException” 即“非法的监视器状态异常”，需要搭配监视器锁使用
        synchronized (object) {
            System.out.println("before wait:");
            // wait 的使用要放到监视器锁(synchronized)里面来调用,保证已经拿到锁了，此时线程状态为 “waiting”;
            object.wait();
            System.out.println("after wait:");
        }
    }
}

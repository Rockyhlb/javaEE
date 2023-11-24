package thread;

/**
 * @BelongsProject: test-11.24
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/24 18:30
 * @Description: synchronized
 * @Author: code_hlb
 */
public class Synchronized {
    public static void main(String[] args) throws InterruptedException {
        /* synchronized特性：互斥、可重入、刷新内存(目前还没有明确说法)
         synchronized 除了可以修饰代码块之外，还可以修饰一个实例方法 或者 一个静态方法
         synchronized 用的锁是存在java对象头里的
         java的一个对象 对应的内存空间中，除了自己定义的一些属性外，还有一些自带的属性，其中就有对象头
         在对象头中，其中就有属性表示当前对象 是否已经加锁*/
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter.increase2();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter.increase2();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(counter.val);
    }
}

class Counter {
    public int val;
    synchronized public void increase() {
        // synchronized 对实例方法的修饰，将 this 作为锁对象进行加锁
        val++;
    }
    // 与上式等价
    public void increase2() {
        synchronized (this) {
            val++;
            // 可重入锁：支持同一个线程第二次加锁，并且通过 引用计数 让最外层的锁解锁，在这之前都不能解锁
            synchronized (this) {
                val++;
            }
        }
    }

    synchronized public static void increase3() {
        // 修饰静态方法相当于是对 类对象 加锁
    }
    public static void increase4() {
        synchronized (Counter.class) {
        }
    }
}

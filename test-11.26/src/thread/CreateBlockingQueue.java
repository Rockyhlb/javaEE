package thread;

/**
 * @BelongsProject: test-11.26
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/26 22:14
 * @Description: 阻塞队列的实现
 * @Author: code_hlb
 */
public class CreateBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        // 借助自定义的阻塞队列实现一个简单的生产者消费者模型
        // 生产者、消费者，分别使用一个线程表示
        MyBlockingQueue queue = new MyBlockingQueue();
        Thread productThread =  new Thread(() -> {
            int num = 0;
            while (true) {
                try {
                    queue.put(++num);
                    System.out.println("生产元素：" + num);
                    // sleep 越多的一方效率越低，另一方消费/生产的速度就越快
                    // Thread.sleep(1000);
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            while (true) {
                try {
                    int res = queue.take();
                    System.out.println("消费元素：" + res);
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        productThread.start();
        consumerThread.start();
    }
}

// 当外面使用 wait 阻塞线程的时候，一定要考虑当前wait 是被 notify() 唤醒的，还是被 interrupt()唤醒的!!
class MyBlockingQueue {
    // 1、基于一个普通的队列
    private static int[] elems;
    private static final int DEFAULT_SIZE = 1000;
    public MyBlockingQueue() {
        elems = new int[DEFAULT_SIZE];
    }
    // head/tail/usedSize 在后续代码中频繁涉及到读和写操作,因此这里都都加上volatile避免内存可见性问题
    private volatile int head;
    private volatile int tail;
    private volatile int usedSize;
    // 入队列
    // wait 不仅可以被 notify 唤醒，也可能会被 interrupt()唤醒，
    // 使用interrupt()唤醒时会出现 InterruptedException异常，因此此处需要往上抛异常使整个方法结束，这种情况下代码没问题
    public void put(int elem) throws InterruptedException {
        // 2、加上线程安全
        // 通过wait 和 notify 实现阻塞 和 唤醒
        synchronized (this) {
            // 使用wait()的时候，往往都是使用while作为条件判定的方式，
            // 目的就是为了让wait被唤醒之后还能再确认一次，避免下面当被interrupt()唤醒时try catch出现的问题
            while (usedSize == elems.length) {
                // 3、加上阻塞
                // 当队列满时阻塞
                this.wait();
                // 当使用try catch 代码块后，当发生异常时，方法并不会结束，而是继续往下执行，
                // 但是现在还是队列满的状态，因此就会导致一个有效元素被覆盖掉。这种情况的代码就有问题
                /*try {
                    this.wait();
                }catch (InterruptedException e) {
                }*/
            }
            elems[tail++] = elem;
            if (tail == elems.length) {
                tail = 0;
            }
            usedSize++;
            // 唤醒take 中的 wait
            this.notify();
        }
    }
    // 出队列
    public int take() throws InterruptedException {
        synchronized (this) {
            while (usedSize == 0) {
                this.wait();
            }
            int res = elems[head++];
            if (head == elems.length) {
                head = 0;
            }
            usedSize--;
            // 唤醒put 中的wait
            this.notify();
            return res;
        }
    }
}

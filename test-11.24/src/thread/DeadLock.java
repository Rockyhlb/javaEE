package thread;

/**
 * @BelongsProject: test-11.24
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/24 19:29
 * @Description: 死锁
 * @Author: code_hlb
 */
public class DeadLock {
    /**
     * 死锁经典问题：哲学家问题
     * 死锁产生的必要条件：
     * 1、互斥使用(锁的基本特性)：当一个线程持有一把锁时，另一个线程也想获得该锁，就会阻塞等待
     * 2、不可抢占(锁的基本特性)：当锁已经被线程1拿到后，其它线程只能等线程1释放，不能强行获取
     * 3、请求保持[占有且等待](代码结构)：一个线程尝试获取多把锁，但是并没有释放当前锁
     * 4、环路等待(代码结构)：等待的依赖关系形成环
     * 解决死锁的方法主要就是破坏上述3、4条件，对于条件3来说，我们可以通过调整代码结构，避免编写 “锁嵌套” 逻辑
     * 对于条件4来说，可以设置加锁的顺序，避免循环等待
     * 银行家算法也可以解决死锁问题，但是这个方法比较复杂，并不实用，因此实际中还是以打破条件3、4居多
     */
    private  static final Object locker1 = new Object();
    private static final Object locker2 = new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (locker1) {
                try {
                    // 让 线程t1 休眠,确保t1 和 t2分别拿到一把锁并加锁，再进行后续操作
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (locker2) {
                    System.out.println("t1 is locked");
                }
            }
//            // 通过避免编写 "锁嵌套" 来解决死锁，但是这种方法不一定好使，因为实际需求中可能就需要 “锁嵌套”;
//            synchronized (locker2) {
//                System.out.println("t1 is locked");
//            }
        });

        Thread t2 = new Thread(() -> {
            // 比较推荐的方式，通过设置加锁顺序打破环路等待，此处约定每次先给编号小(locker1)的加锁
            synchronized (locker1) {
            //synchronized (locker2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (locker2) {
                //synchronized (locker1) {
                    System.out.println("t2 is locked");
                }
            }
        });

        // 最后什么也没打印，因为两个线程都没获取到第二把锁，陷入死锁，两个线程都处于 "BLOCKED"状态
        t1.start();
        t2.start();
    }
}

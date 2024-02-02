
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: test-20240202
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/2 20:25
 * @Description: 线程池
 * @Author: code_hlb
 */
public class ThreadPool {
    public static void main1(String[] args) {
         /*虽然线程相对进程来说已经是轻量级了，但是当我们频繁对线程进行 创建/销毁时，此时的效率也会大幅度拖慢
         因此我们引入了两种进一步提高效率的方法
         1、协程(更轻量级的线程，省略了系统调度的过程),但是目前在java中还不是很普及，在 Go和Python当中用的比较多
         2、线程池:在运行第一个线程时，就提前把其它线程创建好并放在线程池中，后续使用新的线程时，就不需要重新创建了，省去了创建线程的开销
          问：为什么从池子取线程 比 新创建线程效率更高
          答：因为 取 的这个动作是纯用户态的动作，而 创建线程 这个动作 却需要用户态+内核态相互配合完成的动作，而创建线程就需要调用
          系统API进入到内核中，通过内核态的方式完成创建。*/
        // 此处调用了工厂类Executors的工厂方法newCachedThreadPool创建线程池
        // 工厂模式：当我们new一个对象时，我们需要调用类的构造方法，但是当我们需要应对复杂条件下的创建对象时，构造方法的重载可能已经无法满足，
        // 因此就引入了工厂模式，通过使用普通方法代替构造方法完成初始化工作，因此一般工厂方法都是静态方法，方便直接调用
        // 创建线程池对象，线程数目可以动态适应
        ExecutorService service = Executors.newCachedThreadPool();
        // 创建固定数目的线程池对象，线程数目已经指定为 3
        ExecutorService service1 = Executors.newFixedThreadPool(3);
        // 指定只有一个线程的线程池
        ExecutorService service2 = Executors.newSingleThreadExecutor();
        // 相当于一个定时器，但不是只有一个扫描线程负责执行任务了，而是有多个线程执行定时任务
        ExecutorService service3 = Executors.newScheduledThreadPool(3);
        // 上述工厂方法生产的线程池，本质上都是对 ThreadPoolExecutor 这个类的封装
        // ThreadPoolExecutor 核心方法：构造方法、注册任务
        // 构造方法的参数：(可以查找javaAPI 中的juc(java.util.concurrent:存储并发编程相关内容)的ThreadPoolExecutor进行查看)
        // 1#、corePoolSize: 核心线程数
        // 2#、maximumPoolSize: 最大线程数，当该线程池的线程数目可以动态变化时，变化范围就是 [corePoolSize,maximumPoolSize]
        // 3、keepAliveTime: 允许除了核心线程的多余线程活跃的时间
        // 4、TimeUnit unit: 时间单位(ms,s,min)
        // 5、BlockingQueue<Runnable> workQueue: 阻塞队列，用于存放线程池中的任务，
        //  5.1 当任务需要优先级时，此时可以是PriorityQueue;
        //  5.2 当任务不需要优先级，且任务数目相对恒定时，可以是ArrayBlockingQueue
        //  5.3 当任务不需要优先级，且任务数目变化幅度大时，我们可以用LinkedBlockingQueue
        // 6、ThreadFactory threadFactory: 工厂模式的体现，封装了设置线程属性的方法
        // 7#、RejectedExecutionHandler handler: 线程池的拒绝策略(当线程池已满仍想要加入任务时)
        //  7.1 AbortPolicy：直接抛出异常
        //  7.2 CallerRunsPolicy：由提交该任务的线程执行该任务
        //  7.3 DiscardOldestPolicy：放弃最旧的策略，也就是丢弃阻塞队列中最老的任务
        //  7.4 DiscardPolicy：放弃策略，也就是直接将这个待添加任务丢弃
        service1.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("task1");
            }
        });
        /**
         * 问：我们使用线程池时，我们应该设置线程数目为多少合适？(假设计算机有N个CPU核心)
         * 答：具体情况具体分析，因为我们在接触到实际项目代码前，都是无法确定的，
         * 这是由于我们的线程需要执行的代码，大体分为两种类型，分别是：CPU密集型(主要涉及算术运算/逻辑判断) 和 IO密集型，
         * 当我们的线程代码是前者时，我们需要的线程数目顶多就只能设置为N了(此时我们的cpu已经允许满了,就算再添加也无法提高效率)
         * 当线程代码为后者时，IO操作不是很消耗cpu，因此我们就可以设置比N大的数目，以此达到并发执行的效果，
         * 因此实际开发中，我们应该是通过实验的方式，对代码进行反复测试，以此找到最符合预期效果的数值
         */
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool myThreadPool = new MyThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            // 匿名内部类中涉及变量捕获(只能是final或者类final的变量)
            int temp = i;
            myThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("num: " + temp);
                }
            });
        }
    }
}

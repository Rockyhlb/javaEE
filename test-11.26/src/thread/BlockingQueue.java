package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @BelongsProject: test-11.26
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/26 21:11
 * @Description: 阻塞队列的定义和使用
 * @Author: code_hlb
 */
public class BlockingQueue {
    /**
     * 阻塞队列：多线程代码中比较常用的一种数据结构  是线程安全的 并且带有阻塞特性
     *  1) 如果队列为空，继续出队列，就会发生阻塞，阻塞到其它线程往队列里添加元素为止
     *  2) 如果队列为满，继续入队列，也会发生阻塞，阻塞到其它线程从队列中取走元素为止
     * 阻塞队列，最大的意义就是用来实现 “生产者消费者模型”(一种常见的多线程代码编写方式)
     * <p>
     * 生产者消费者模型的意义：1、解耦合：两个模块联系越紧密，耦合度越高，当一个模块出现bug时，自然会牵连到另一个模块
     *                    2、削峰填谷：当短时间内的请求达到峰值时，阻塞队列就会起到一个缓冲作用，缓解一定的访问压力
     */
    public static void main(String[] args) throws InterruptedException {
        // BlockingQueue 接口 提供两种实现方式，分别是LinkedBlockingDeque
        java.util.concurrent.BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        java.util.concurrent.BlockingQueue<Integer> linkedBlockingDeque = new LinkedBlockingQueue<>();

        // BlockingDeque 也提供了offer 和 poll方法，但是并不具备 阻塞 的特性
        // put() 阻塞式的入队列      take() 阻塞式的出队列   并没有阻塞式的获取队首元素
        linkedBlockingDeque.put(1);
        linkedBlockingDeque.put(2);
        linkedBlockingDeque.put(3);
        linkedBlockingDeque.put(4);
        int elem = linkedBlockingDeque.take();
        System.out.println(elem);
        elem = linkedBlockingDeque.take();
        System.out.println(elem);
        elem = linkedBlockingDeque.take();
        System.out.println(elem);
        elem = linkedBlockingDeque.take();
        System.out.println(elem);
        // 此时程序进入阻塞，程序既不会往下运行，也不会结束退出
        elem = linkedBlockingDeque.take();
        System.out.println(elem);
    }
}

package Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @BelongsProject: test-20240203
 * @BelongsPackage: Thread
 * @CreateTime : 2024/2/3 21:25
 * @Description: 集合框架中线程安全的集合
 * @Author: code_hlb
 */
public class ThreadSecureInCollection {
    public static void main(String[] args) {
        // 集合框架当中的大部分集合，都是线程不安全的
        // 线程安全的只有：Vector,HashTable,Stack --> synchronized
        // 针对线程不安全的集合类，要想在多线程环境下使用，有以下办法：
        // 1、加锁，synchronizedList()会返回一个新的对象，这个对象套上了synchronized
        Collections.synchronizedList(new ArrayList());

        // 2、CopyOnWriteArrayList()写时拷贝,修改线程先修改副本，修改完毕以后再将引用赋值回去，这样就不影响读线程了
        // 但是局限性也很高，首先这个数据结构不能太大(拷贝成本高)，其次只适用于只有一个修改线程，不能多个写线程同时操作
        // 3、线程安全的阻塞队列:ArrayBlockingQueue,LinkedBlockingQueue,PriorityBlockingQueue
        // 4、ConcurrentHashMap:
        // HashTable 保证线程安全主要就是给关键方法加上synchronized,相当于给this加锁，这就导致只要两个线程操作同一个HashTable就会锁冲突
        // HashTable 底层由数组+链表/二叉搜索树来解决Hash冲突，其实当我们操作不同链表时是线程安全的，只有操作同一个链表才需要加锁
        // 4.1、ConcurrentHashMap 就是基于此，将一个全局的大锁，换成了多个局部的小锁，这样也就大幅度降低了锁冲突的频率，
        // java8之前，ConcurrentHashMap是基于分段锁来实现的(多个链表共用一把锁)
        // java8之后，ConcurrentHashMap就成了直接在链表头加锁了
        // 4.2、充分利用了CAS特性，把一些不必要加锁的环节给省略了
        // 4.3、ConcurrentHashMap 针对读操作没有加锁，因为读与读，读与写之间都不会产生竞争
        // 4.4、ConcurrentHashMap 针对扩容操作做了单独的优化，采用批处理搬运的方式，避免一次搬运过多数据产生卡顿
    }
}

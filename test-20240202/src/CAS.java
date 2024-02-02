
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: test-20240202
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/2 23:16
 * @Description: CAS & ABA
 * @Author: code_hlb
 */
public class CAS {
    // 一、CAS: Compare And Swap,本质上是一个cpu上的原子指令，比较交换的是内存和寄存器
    // CAS(M,A,B)：如果M 和 A的值相同,就把M 和 B里的值进行交换，同时整个操作返回true，反之什么也不做且返回false
    // 基于CAS实现线程安全的方式，也称为”无锁编程“
    //  优点: 保证线程安全，同时避免了阻塞(效率提高)
    //  缺点: 代码更复杂，易读性不好，且只适用于特定场景，不如加锁操作普遍适用

    // 实现原子类：
    // 这里的 int++, 不是原子操作(load,add,save),因此存在线程安全问题
    // 这里导致的线程不安全本质上就是这里的自增操作穿插执行了
    //  加锁是通过阻塞的方式，避免穿插
    //  CAS则是通过重试(反复比较)的方式，避免穿插
    // public static int count = 0;
    // AtomicInteger,基于CAS的方式对int进行了封装，此时自增操作就是原子的了
    public static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                // count++;
                /*public final int getAndIncrement() {
                    return unsafe.getAndAddInt(this, valueOffset, 1);
                  }*/
                // 在java中，有些操作是偏底层的操作，这些操作使用时不小心就容易出现问题，因此将其都归在 ”unsafe“类中
                count.getAndIncrement();
                // count--;
                // count.getAndDecrement();
                // ++count
                // count.incrementAndGet();
                // --count
                // count.decrementAndGet();
            }
        });
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                // count++;
                count.getAndIncrement();
            }
        });
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        // System.out.println(count);
        System.out.println(count.get());
    }

    // 二、ABA问题：CAS 是通过比较值”没有发生变化“来作为”没有其它线程穿插执行“的判定依据，
    //   但是，当中途有一个线程进来将A->B->A,针对前一个线程来说，好像没有发生改变，但实际上已经发生改变了
    // 一般情况下，只有极端情况才会使ABA出现bug
    // 解决方法：让判定的数值朝着一个方向增长即可，不要又增又减，此时就可引入一个额外变量(例如版本号)进行判定
}

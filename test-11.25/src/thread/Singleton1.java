package thread;

/**
 * @BelongsProject: test-11.26
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/26 20:13
 * @Description: 指令重排序
 * @Author: code_hlb
 */
public class Singleton1 {
    /**
     * 指令重排序：也是一种编译器优化，指编译器为了执行效率，会调整代码的执行顺序，调整的前提是保持逻辑不变
     * 通常情况下，能够保证代码逻辑不变的情况下，把代码执行效率大幅度提高
     * 因此以下代码如果在不加volatile 的情况下，仍然可能会出现线程不安全问题
     */
    // 3、volatile 解决指令重排序问题
    private static volatile Singleton1 instance;
    private Singleton1() {}
    public static Singleton1 getInstance() {
        // 2、再加一个判定解决 单例模式只需要第一次访问需要加锁的问题
        /*
          new 操作具体可以分为三步: 1、申请内存空间 2、在内存空间上构造对象(调用构造方法) 3、把内存的地址赋值给 instance 引用
          如果编译器优化代码执行顺序为1->3->2，当线程1执行完1、3，同时已经被加锁了，此时 instance 指向的是一个
          还没初始化的非法对象,但是线程2是先进行第一重判定instance不为空,因此无论加不加锁,线程2都只会返回instance导致程序出错
         */
        if (instance == null) {
            // 1、通过加锁解决线程不安全中的 多个线程修改同一个变量 的问题
            synchronized (Singleton1.class) {
                if (instance == null) {
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}

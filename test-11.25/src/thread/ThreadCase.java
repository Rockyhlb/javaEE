package thread;

/**
 * @BelongsProject: test-11.25
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/25 19:07
 * @Description: 多线程的代码案例
 * @Author: code_hlb
 */
public class ThreadCase {
    /**
     * 设计模式：从实际开发中经常遇见的场景中提取出来的一些模板解决方案，类似于棋谱，提高了代码能力的下限
     * 校招中考察的设计模式最常见的就是 *单例模式* 和 *工厂模式*
     * 单例(单个实例)模式：有些开发场景中，希望有的类只能有一个对象，不能有多个对象
     */
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1);
        System.out.println(singleton2);
    }
}

/**
 * 如果多个线程，同时修改同一个变量，此时就可能出现线程安全问题
 * 饿汉模式(线程安全)：线程调用 getInstance() 只是读取操作，并不会修改变量
 * 懒汉模式(线程不安全)：线程调用 getInstance() 涉及读取和修改操作，因此可能会发生线程不安全问题，也就违背了单例的要求，
 * 可以通过加锁操作解决懒汉模式的线程不安全问题，但是加锁操作也就导致了这个代码基本上 不可能是 ”高性能“ 代码;
 */
class Singleton {
    // 1、声明一个静态的实例对象
    // 饿汉模式(类加载时单例对象就被创建)：private static Singleton instance = new Singleton();
    private static Singleton instance;  // 懒汉模式: 如果没有调用getInstance()，就不会创建实例，因此节省了创建实例的开销
    // 2、将构造方法设置为私有，其它类就不能 new 出这个类的对象了
    private Singleton() {
    }
    // 3、提供一个公共方法获取当前的实例
    public static Singleton getInstance() {
        // 懒汉模式，非必要不创建，节省内存开销从而提高了效率，但同时也带来了线程安全问题
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    public static Singleton getInstance1() {
        // 虽然这样解决了线程不安全问题，却也导致了每次调用方法时都会进行加锁操作，但是我们的单例模式其实只要第一次加锁就行了
//        synchronized (Singleton.class) {
//            if (instance == null) {
//                instance = new Singleton();
//            }
//        }
        // 外面再加一个判定条件，当实例对象为空时，再进行加锁操作保证线程安全。当对象已经有了，那线程就是安全的，就不需要加锁了
        if(instance != null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

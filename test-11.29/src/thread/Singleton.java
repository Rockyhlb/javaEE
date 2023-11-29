package thread;

/**
 * @BelongsProject: test-11.29
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/29 9:20
 * @Description: 单例模式的实现
 * @Author: code_hlb
 */
public class Singleton {
    public static void main(String[] args) {
        SingleModule singleModule1 = SingleModule.getInstance();
        SingleModule singleModule2 = SingleModule.getInstance();
        System.out.println(singleModule1.hashCode());
        System.out.println(singleModule2.hashCode());
    }
}

class SingleModule {
    // 1、声明一个私有化的类对象
    // 1.3 将实例添加关键字 volatile，防止指令重排序(new 一个对象可以分解为三步)
    // (一、分配一个内存空间 二、在这个内存地址上创建对象(构造方法) 三、将这个地址赋值给instance)
    // 因此当指令重排序为1-3-2的顺序时就会出问题
    private static volatile SingleModule instance; // 懒汉模式，线程不安全
    // private static SingleModule instance = new SingleModule(); // 饿汉模式，线程安全
    // 2、私有化构造方法
    private SingleModule() {}
    // 3、提供一个public方法提供实例
    public static SingleModule getInstance() {
        // 1.2 再加一重循环，判定是否需要加锁，单例模式只需要第一次为空时判定加锁就可
        if (instance == null) {
            // 1.1 通过加锁 限制多个线程对同一个变量(共享变量)的访问，从而达到线程安全的效果
            synchronized (SingleModule.class) {
                if (instance == null) {
                    instance = new SingleModule();
                }
            }
        }
        return instance;
    }
}

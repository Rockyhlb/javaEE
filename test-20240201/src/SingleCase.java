/**
 * @BelongsProject: test-20240201
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/1 23:32
 * @Description: 实现懒汉模式下的线程安全的单例模式, 饿汉模式线程安全不需加锁
 * @Author: code_hlb
 */
public class SingleCase {
    // 1、声明一个单例对象(懒汉模式-非必要不创建)
    private static SingleCase singleCace = null;
    // 2、私有化构造方法
    private SingleCase() {}
    // 3、对外提供一个可以访问的方法接口
    public SingleCase getInstance() {
        // 多线程时，由于线程可能会对同一个变量进行读写，因此需要进行加锁\
        // 但是由于单例模式只设计到第一次访问时需要对 singleCace 进行修改，因此其实只需加一次锁即可
        if (singleCace == null) {
            synchronized (SingleCase.class) {
                if (singleCace == null) {
                    singleCace = new SingleCase();
                }
            }
        }
        return singleCace;
    }
}

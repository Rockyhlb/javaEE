package thread;

/**
 * @BelongsProject: test-11.22
 * @BelongsPackage: thread
 * @CreateTime : 2023-11-23 16:17
 * @Description: 5、最推荐的写法：通过 lambda 表达式替代匿名内部类来创建线程
 * @Author: code_hlb
 */
public class Demo5 {
    public static void main(String[] args) throws InterruptedException {
        // lambda 表达式的本质还是匿名函数(只运行一次)，主要用来实现 "回调函数" 的效果
        // 回调函数：不是主动调用的，也不是现在就立即调用，而是把调用的机会交给别人(通常是操作系统、库、框架、
        // 或者别人写的代码)来进行使用，别人会在合适的时机调用这个函数
        Thread thread5 = new Thread(() -> {
            while (true) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        thread5.start();
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}

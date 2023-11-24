package thread;

import java.util.Scanner;

/**
 * @BelongsProject: test-11.24
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/24 22:30
 * @Description: 线程安全问题 --> 内存可见性
 * @Author: code_hlb
 */
public class VolatileDemo {
    // 此处的 volatile 作用就是告诉编译器不要优化(当前情况就是优化快了，但是结果不准了，也就出现了bug)
    private volatile static int flag = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (flag == 0) {
                /* 计算机运行程序访问的数据往往存储在内存中，cpu使用这个变量时，就会先把这个数据先读出来，
                 再放到cpu的寄存器中(load操作)，相比于读内存,读寄存器快很多。
                 当循环体中没有任何代码时，此时循环一秒钟会执行很多次，也就会产生很多的 load 和 cmp 操作，
                 而一次load花费的时间相当于上万次cmp了，因此就会触发
                 编译器优化：把本来要读内存的操作，优化成读取寄存器,从而减少读内存的次数,提高运行效率，最终编译器就只在
                 第一次循环的时候才读了内存,后面就不再读内存了,而是直接从寄存器中取出flag的值(也就读取不到内存中已经更改的flag);*/

                // 加入sleep以后，while循环速度慢了，因此load操作的开销也小了，就不会触发编译器优化，也就不会出现内存可见性问题
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }
            System.out.println("thread is end.");
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println("请输入中断线程指令(1): ");
            Scanner scanner = new Scanner(System.in);
            // 一旦输入为1则结束线程t1
            flag = scanner.nextInt();
        });
        t2.start();
    }
}

package thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @BelongsProject: test-11.28
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/28 15:54
 * @Description: 测试类
 * @Author: code_hlb
 */
public class TimerTest {
    public static void main(String[] args) {
        // 当需要多线程调试时，不建议使用断点调试，因为断点会影响到某些线程的执行顺序
        // 因此当外面需要调试多线程程序时，一般通过 "打印日志” 来调试，即println()
        MyTimer myTimer = new MyTimer();
        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("定时器2000，启动~" + System.currentTimeMillis());
            }
        },2000);
        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("定时器4000，启动~" + System.currentTimeMillis());
            }
        },4000);
        System.out.println("定时器开始计时...");
    }
    public static void main1(String[] args) {
        Timer timer = new Timer();
        // 当定时器任务完成以后，整个进程并不会结束，这是因为虽然此时主线程结束了，
        // 但是Timer内部的线程(扫描线程)阻止了进程结束
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器1000，启动~" + System.currentTimeMillis());
            }
        },1000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器2000，启动~" + System.currentTimeMillis());
            }
        },2000);
        System.out.println("定时器启动成功~" + System.currentTimeMillis());
    }
}

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @BelongsProject: test-20240201
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/1 19:25
 * @Description: 回顾之前编写的自定义定时器代码
 * @Author: code_hlb
 */
public class Demo1 {

    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();
        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("5000ms later...");
            }
        },5000);

        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("2000ms later...");
            }
        },2000);
        System.out.println("程序开始启动..");
    }

    public static void main1(String[] args) {
        Timer timer = new Timer();
        System.out.println("now: " + System.currentTimeMillis());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("2000 later..." + System.currentTimeMillis());
            }
        },2000);
        System.out.println("程序开始启动..");
        // 定时器任务完成以后，进程仍然不会终止，因为定时器内部有一个扫描线程一直扫描任务队列(阻止进程的结束)
    }
}

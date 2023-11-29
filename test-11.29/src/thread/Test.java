package thread;

/**
 * @BelongsProject: test-11.29
 * @BelongsPackage: thread
 * @CreateTime : 2023/11/29 8:55
 * @Description: 测试类
 * @Author: code_hlb
 */
public class Test {
    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();
        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("2s later.....");
            }
        },2000);

        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("4s later.....");
            }
        },4000);

        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("6s later.....");
            }
        },6000);
        System.out.println("定时器已加载...");
    }
}

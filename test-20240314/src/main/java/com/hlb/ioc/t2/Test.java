package com.hlb.ioc.t2;

/**
 * @BelongsProject: test-20240314
 * @BelongsPackage: com.hlb.ioc.t1
 * @CreateTime : 2024/3/14 10:50
 * @Description: TODO
 * @Author: code_hlb
 */
public class Test {
    public static void main(String[] args) {
        // 传统意义上造车，车依赖于车身，车身依赖于底盘，底盘依赖于轮胎，它们之间的耦合度过高
        // 当我们想要对其传入一个尺寸时，这会导致每个相关联的类都会进行修改，无疑代码的维护成本过高
        // 现在我们将依赖类提取出来，我们只需要直接取就行
        Tire tire = new Tire(30,"red");
        Bottom bottom = new Bottom(tire);
        FrameWork frameWork = new FrameWork(bottom);
        // Spring IOC 控制反转就是完成类似上述工作
        // 控制反转就是对 获取依赖对象的过程进行了反转
        Car car = new Car(frameWork);
        car.run();
    }
}

package IO;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @BelongsProject: test-20240203
 * @BelongsPackage: IO
 * @CreateTime : 2024/2/3 22:02
 * @Description: File的基本操作
 * @Author: code_hlb
 */
public class Demo1 {
    public static void main1(String[] args) throws IOException {
        // 文件：可以理解为是硬盘数据的一种抽象，分为 文本文件 和 二进制文件
        // 文本文件：文件中保存的数据，都是字符串，保存的内容都是合法的字符
        // 二进制文件：文件中保存的数据，仅仅是二进制数据，保存的内容可能不是合法的字符
        // 如果文件是utf8编码的，此时文件中的每个数据都是合乎utf8编码的字符，那么就可认为这是文本文件，反之则是二进制文件
        // 很多文件都是二进制的，.docx .pdf .pptx这些其实都是二进制文件

        // java针对文件的操作，分成两类：
        // 1、文件系统的操作：创建文件/删除文件/判定文件是否存在/重命名 ...
        // pathSeparator: 一个路径中用来分割目录的符号，win采用“/和\”，linux和mac 采用“/”
        // File file = new File("D:/Code/java_code/javaEE/test-20240203/src/00.txt");
        File file = new File("./00.txt");
        System.out.println(file.getParent()); // 获取父目录
        System.out.println(file.getName()); // 获取文件名
        System.out.println(file.getPath()); // 获取文件路径
        System.out.println(file.getAbsolutePath());  // 获取绝对路径=基准目录(项目所在目录)+当前的相对路径
        System.out.println(file.getCanonicalFile()); // 获取修饰过的绝对路径(针对绝对路径进行简化后得到的路径)
    }

    public static void main2(String[] args) throws IOException, InterruptedException {
        File file = new File("./00.txt");
        System.out.println("------------------------");
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("是否是目录文件：" + file.isDirectory());
        System.out.println("是否是普通文件：" + file.isFile());

        System.out.println("------------------------");
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("创建文件：" + file.createNewFile());
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("是否是目录文件：" + file.isDirectory());
        System.out.println("是否是普通文件：" + file.isFile());

        System.out.println("------------------------");
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("删除文件：" + file.delete());
        System.out.println("文件是否存在：" + file.exists());
    }

    public static void main3(String[] args) throws InterruptedException, IOException {
        File file = new File("./00.txt");
        System.out.println("------------------------");
        System.out.println("创建文件：" + file.createNewFile());
        // 在进程结束后删除
        // office 打开一个文件时，就会产生一个临时文件，一旦手动关闭了office,这个文件就会自动被删除(使用类似于deleteOnExit的机制)，
        // 但是当出现断电等原因产生的异常关闭来不及保存时，就可以通过这个临时文件恢复之前的工作
        file.deleteOnExit();
        Thread.sleep(5000);
        System.out.println("进程结束");
    }

    public static void main4(String[] args) throws IOException {
        File file = new File("d:/");
        System.out.println("------------------------");
        System.out.println("创建文件：" + file.createNewFile());
        // list() 返回File对象目录下的所有文件名,返回的是String[]
        // listFiles()同理，返回的是File[]
        String[] strings = file.list();
        // System.out.println(strings); // 打印出来的"[Ljava.lang.String;@1b6d3586"并不是地址！！而是hash值！！！
        // 在JVM上层，java代码是没有任何办法获取到内存地址的，要想拿到只能靠native进入jvm内部通过C++代码获取
        System.out.println(Arrays.toString(strings));

        File file1 = new File("./00");
        // 创建目录
        System.out.println(file1.mkdir());
        // 创建多级目录
        File file2 = new File("./00/01/02");
        System.out.println(file2.mkdirs());
    }

    public static void main5(String[] args) throws IOException {
        // 重命名
        File srcFile = new File("./00.txt");
        System.out.println(srcFile.createNewFile());

        File descFile = new File("./00.txt");
        System.out.println(srcFile.renameTo(descFile));
    }
}

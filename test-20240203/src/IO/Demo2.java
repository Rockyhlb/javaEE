package IO;

import java.io.*;

/**
 * @BelongsProject: test-20240203
 * @BelongsPackage: IO
 * @CreateTime : 2024/2/3 23:08
 * @Description: Reader & Writer
 * @Author: code_hlb
 */
public class Demo2 {
    public static void main1(String[] args) {
        // 2、文件内容的操作(流对象)：读文件/写文件
        // 在标准库中，提供的读写文件的流对象，由很多种类，但是大体上其实可以划分为以下两类：
        // 2.1 字节流(二进制文件)：每次读/写的最小单位，都是字节(8bit)
        // 2.2 字符流(文本文件)：每次读写的最小单位，都是字符(一个字符对应多个字节)，GBK的一个中文字符占2个字节，utf8的一个中文字符占3个字节
        // 字符流本质上是针对字节流的又一层封装，就能自动把文件中几个相邻的字节，转换成一个字符(帮我们完成了自动查字符集表)
        // 输入输出的定义应该以CPU的角度为基准
        // 字节流提供了两个抽象父类(InputStream/OutputStream)
        // 字符流提供了两个抽象父类(Reader/Writer)
    }

    public static void main2(String[] args) throws IOException {
        // 创建Reader对象就是”打开文件“的过程
        // 1) 一次读取一个字符
        Reader reader = new FileReader("./00.txt");
        while (true) {
            // public int read() throws IOException {} 返回的是整型
            int ch = reader.read();
            // 无参数read(): 一次读取一个字符
            // 一个参数read(char[] cbuf): 一次读取若干字符，把参数指定的cbuf数组填充满
            // 三个参数read(char[] cbuf,int off,int len)：一次读取若干字符，把cbuf数组中从off位置开始到len长度范围的位置填满
            if (ch == -1) {
                // 源码中返回-1表示文件已经读取完毕
                break;
            }
            // 在java标准库内部，如果是只使用了char,此时使用的字符集，固定就是Unicode
            // 如果是使用了String,此时就会自动的把每个字符的Unicode 转换成 Utf8
            // String str = new String(a); 就会在内部把每个字符转换utf8
            // str.charAt(i) 也就会把对应的utf8数据 转换成 Unicode
            System.out.println((char) ch);
        }
    }

    public static void main3(String[] args) throws IOException {
        // 2.0) 一次读取多个字符
        /*while (true) {
            // 此处的cbuf这个参数，就是”输出型参数“
            char[] cbuf = new char[128];
            // 返回查询到的字符数量
            int res = reader.read(cbuf);
            if (-1 == res) {
                break;
            }
            System.out.println("num = " + res);
            for (int i = 0; i < res; i++) {
                System.out.println(cbuf[i]);
            }
        }
        // 最后，一个文件使用完毕，一定要close()释放掉文件描述符
        // 进程->PCB->pid/内存指针/文件描述符表(顺序表)，
        // 当我们一直打开文件而不关闭文件，就会使这个顺序表占满，后续再打开文件就会出错(文件资源泄露)
        // 但是现在这种写法，仍然有文件资源泄露的风险，当我们前面的代码出现异常时，此处的关闭操作就无法执行到
        reader.close();*/

        // 2.1)
        /*try {
            while (true) {
                char[] cbuf = new char[128];
                int res = reader.read(cbuf);
                if (-1 == res) {
                    break;
                }
                System.out.println("num = " + res);
                for (int i = 0; i < res; i++) {
                    System.out.println(cbuf[i]);
                }
            }
        }finally {
            // 采用try-finally的方式关闭资源
            reader.close();
        }*/

        // 2.2) 最推荐的方式:try with resources, try()括号里定义的变量会在try代码块结束的时候自动调用其中的close方法
        // 因此也就要求这个对象必须要实现Closeable接口,流对象都能写入
        try (Reader reader = new FileReader("./00.txt")) {
            while (true) {
                char[] cbuf = new char[128];
                int res = reader.read(cbuf);
                if (-1 == res) {
                    break;
                }
                System.out.println("num = " + res);
                for (int i = 0; i < res; i++) {
                    System.out.println(cbuf[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Writer的使用
        try(Writer writer = new FileWriter("./00.txt",true)) {
            // 默认情况下是”覆盖式写入“
            // Writer writer = new FileWriter("./00.txt");
            writer.write("我爱学习");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package Io;

import java.io.*;
import java.util.Scanner;

/**
 * @BelongsProject: test-20240205
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/5 0:06
 * @Description: InputStream & OutputStream
 * @Author: code_hlb
 */
public class Demo1 {
    public static void main1(String[] args){
        // inputStream 操作二进制文件
        try(InputStream inputStream = new FileInputStream("./00.txt")) {
            byte[] buffer = new byte[1024];
            int resNum = inputStream.read(buffer);
            for (int i = 0; i < resNum; i++) {
                // 实际打印出来的是ASCII码表的十六进制数据
                System.out.printf("%x\n",buffer[i]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main2(String[] args) {
        // OutputStream 输出数据
        // 传入参数后面添加一个true，表示“追加写”
        try(OutputStream outputStream = new FileOutputStream("./00.txt",true)) {
            String str = "我爱学习";
            outputStream.write(str.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main3(String[] args){
        try(InputStream inputStream = new FileInputStream("./00.txt")) {
            // 提供的是字节流对象，但是我们知道实际文本内容是文本数据，因此我们可以将字节流转换成字符流输出
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main4(String[] args) {
        try(OutputStream outputStream = new FileOutputStream("./00.txt",true)) {
            // PrintWriter就相当于把字节流转换成字符流了
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println("我爱学习");
            // 此时文本内容中并没有成功添加内容，这是因为PrintWriter这样的类,写入内容一般不会直接写入硬盘，而是先把数据存储到一个
            // 由内存构成的“缓冲区(buffer)”中，以此提高效率,这是由于计算机每次将数据写入到硬盘的开销都很大，因此就要减少写入硬盘的次数
            // 所以此处当进程结束时，缓冲区的内容还未写入硬盘，就需要调用flush()方法刷新一下缓冲区
            printWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

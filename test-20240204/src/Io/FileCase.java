package Io;

import java.io.File;
import java.util.Scanner;

/**
 * @BelongsProject: test-20240204
 * @BelongsPackage: Io
 * @CreateTime : 2024/2/5 0:40
 * @Description: 文件操作的综合使用
 * @Author: code_hlb
 */
public class FileCase {
    public static void main(String[] args) {
        // 需求：扫描指定目录，并找到名称中包含指定字符的所有普通文件(不包含目录),并且询问用户是否要删除文件
        Scanner scanner = new Scanner(System.in);
        // 1、读取用户给定扫描目录
        System.out.print("请输入需要扫描的目录：");
        String path = scanner.nextLine();
        File rootPath = new File(path);
        if (!rootPath.isDirectory()) {
            System.out.println("您输入的路径有误~");
            return;
        }
        // 2、读取用户给定关键词
        System.out.print("请输入需要删除目录的关键词：");
        String key = scanner.next();
        // 3、开始扫描目录，进行递归
        showDir(rootPath,key);
    }

    private static void showDir(File rootPath,String key) {
        // 1、通过listFiles() 方法返回当前根目录下的所有文件对象
        File[] files = rootPath.listFiles();
        if (files == null) {
            // 当前目录已没有子目录或文件
            return;
        }
        // 2、针对当前文件/目录进行不同处理
        for(File curFile : files) {
            if (curFile.isDirectory()) {
                // 进入递归，类似于先序遍历
                showDir(curFile,key);
            }else {
                checkDelete(curFile,key);
            }
        }
    }
    private static void checkDelete(File deleteFile,String key) {
        if (deleteFile.getName().contains(key)) {
            System.out.print("确定删除当前文件(Y/N):" + deleteFile.getPath() + "? :");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            if (choice.equals("Y") || choice.equals("y")) {
                deleteFile.delete();
                System.out.println("删除成功~");
            }else {
                System.out.println("取消成功~");
            }
        }else {
            return;
        }
    }
}

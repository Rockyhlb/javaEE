package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: test-20240207
 * @BelongsPackage: network
 * @CreateTime : 2024/2/7 20:15
 * @Description: 基于TCP的“回显服务器”
 * @Author: code_hlb
 */
public class TcpEchoServer {
    private ServerSocket serverSocket = null;

    public TcpEchoServer(int port) throws IOException {
        // serverSocket是给服务器使用的类，使用这个类来绑定端口号，也是对网卡的抽象
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true) {
            System.out.println("Tcp服务器启动！");
            // Socket既会给服务器端使用，也会给客户端使用
            // 对于应用程序来说，客户端这边主要负责建立连接的动作，而服务器端主要是把建立好的连接从系统内核中取出
            // 此处的accept()就是直接将系统内核(底层可以认为是一个生产者消费者模型)中的连接拿到应用程序中
            // 由于该重循环中，每次进来后都会产生一个新的clientSocket对象，因此后续需要close，防止文件资源泄露
            Socket clientSocket = serverSocket.accept();
            // 这种写法当出现第一个客户端开启后就会阻塞在processConnection的scanner.next()中,当第二个客户端进入后就无法执行这里的accept()
            // 因此就需要并发的执行这里的任务
            /* processConnection(clientSocket);*/
            // 创建新的线程调用processConnection()，这样主线程就可以继续执行serverSocket.accept()方法;
            /*Thread thread = new Thread(() -> {
               processConnection(clientSocket);
            });
            thread.start();*/
            // 更好一点的优化方法就是使用线程池创建线程，减少频繁创建/销毁线程的开销
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    processConnection(clientSocket);
                }
            });
            // 此处进行clientSocket.close();也是不可取的，因为当processConnection抛出异常时，此处的代码就无法被执行到
        }
    }

    private void processConnection(Socket clientSocket) {
        // 打印日志，说明当前已有客户端建立连接
        System.out.printf("[%s:%d] 客户端上线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
        // 接下来进行数据的交互,由于TCP是基于字节流传输的，因此我们可以使用InputStream和OutputStream进行网络的通信
        try(InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()) {
            // 针对客户端输入的多条数据进行循环处理
            while (true) {
                Scanner scanner = new Scanner(inputStream);
                if (!scanner.hasNext()) {
                    // 当连接断开时，说明当前客户端已经下线，需要退出循环
                    System.out.printf("[%s:%d] 客户端下线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
                    break;
                }
                // 1、读取请求并解析，此处以next()的方式进行读取，当读到”空白符“时就返回，此处约定使用换行符(\n)作为结束标记
                String request = scanner.next();
                // 2、根据请求计算响应，此处响应就按照原数据返回
                String response = process(request);
                // 3、将响应写回到客户端
                PrintWriter printWriter = new PrintWriter(outputStream);
                //  此处使用println(带有\n)的作用就是为了让客户端使用 scanner.next()读取响应内容
                printWriter.println(response);
                // 属性一下缓冲区
                printWriter.flush();
                // 4、打印日志，记录本次交互的内容
                System.out.printf("[%s,%d] req=%s,resp=%s\n",clientSocket.getInetAddress(),clientSocket.getPort(),
                        request,response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer tcpEchoServer = new TcpEchoServer(9090);
        tcpEchoServer.start();
    }
}

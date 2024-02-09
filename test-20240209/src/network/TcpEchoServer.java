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
 * @BelongsProject: test-20240209
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/9 21:49
 * @Description: TODO
 * @Author: code_hlb
 */
public class TcpEchoServer {
    private ServerSocket serverSocket = null;
    public TcpEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器端上线！");
        // 采用线程池并发执行任务，允许多客户端同时访问
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 从内核中获取建立的客户端连接
        while (true) {
            Socket clientSocket = serverSocket.accept();
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        process(clientSocket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
    private void process(Socket clientSocket) throws IOException {
        System.out.printf("[%s:%d] 客户端上线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
        try(InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()) {
            while (true) {
                Scanner scanner = new Scanner(inputStream);
                if (!scanner.hasNext()) {
                    // 此时客户端已经断开连接
                    System.out.printf("[%s:%d] 客户端下线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
                    break;
                }
                // 1、获取请求
                String request = scanner.next();
                // 2、计算响应
                String response = processNetWork(request);
                // 3、返回响应结果
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println(response);
                printWriter.flush();
                // 4、打印日志
                System.out.printf("[%s:%d] req=%s,resp=%s\n",clientSocket.getInetAddress(),clientSocket.getPort(),
                                  request,response);
                }
            }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }finally {
            // 记得关闭客户端的socket，防止文件资源泄露
            clientSocket.close();
        }
    }
    private String processNetWork(String request) {
        return request;
    }
    public static void main(String[] args) throws IOException {
        TcpEchoServer tcpEchoServer = new TcpEchoServer(9090);
        tcpEchoServer.start();
    }
}

package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @BelongsProject: test-20240207
 * @BelongsPackage: network
 * @CreateTime : 2024/2/7 20:15
 * @Description: 基于TCP的“回显服务器”客户端
 * @Author: code_hlb
 */
public class TcpEchoClient {
    private Socket socket = null;

    public TcpEchoClient(String serverIp,int ServerPort) throws IOException {
        // 由于TCP是有连接的，因此就需要在创建Socket的同时，和服务器建立连接
        socket = new Socket(serverIp,ServerPort);
    }

    public void start() {
        System.out.println("客户端启动！");
        Scanner scanner = new Scanner(System.in);
        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()) {
            Scanner scannerNetwork = new Scanner(inputStream);
            PrintWriter writer = new PrintWriter(outputStream);
            while (true) {
                System.out.print("-> ");
                // 1、发送请求
                String request = scanner.next();
                writer.println(request);
                writer.flush();
                // 2、获取响应
                String response = scannerNetwork.next();
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient tcpEchoClient = new TcpEchoClient("127.0.0.1",9090);
        tcpEchoClient.start();
    }
}

package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @BelongsProject: test-20240209
 * @BelongsPackage: network
 * @CreateTime : 2024/2/9 21:50
 * @Description: TODO
 * @Author: code_hlb
 */
public class TcpEchoClient {
    private Socket socket = null;
    public TcpEchoClient(String serverIp,int serverPort) throws IOException {
        socket = new Socket(serverIp,serverPort);
    }
    private void start() {
        System.out.println("客户端上线！");
        Scanner scanner = new Scanner(System.in);
        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()) {
            Scanner scannerNetWork = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);
            while (true) {
                System.out.print("-> ");
                // 1、获取请求
                String request = scanner.next();
                // 2、发送请求
                printWriter.println(request);
                printWriter.flush();
                // 3、获取响应
                String response = scannerNetWork.next();
                // 4、打印日志
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

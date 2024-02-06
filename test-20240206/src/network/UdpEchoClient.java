package network;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @BelongsProject: test-20240206
 * @BelongsPackage: network
 * @CreateTime : 2024/2/6 22:00
 * @Description: "回显服务器"的客户端程序
 * @Author: code_hlb
 */
public class UdpEchoClient {
    private DatagramSocket socket = null;
    private String serverIp = "";
    private int serverPort = 0;

    public UdpEchoClient(String ip,int port) throws SocketException {
        // 客户端端口号由系统自动分配
        socket = new DatagramSocket();
        // 由于Udp自身不会持有对端的信息，因此需要指定目的端(服务器端)的信息
        serverIp = ip;
        serverPort = port;
    }

    public void start() throws IOException {
        System.out.println("客户端启动！");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 1、从控制台读取数据，作为请求
            System.out.print("-> ");
            String request = scanner.nextLine();
            // 2、把请求打包成DatagramPacket对象(传入的是二进制数据)，并发送给服务器端
            //  在构造方法中指定字节数组缓冲区和ip+端口号
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),0,
                    request.getBytes().length, InetAddress.getByName(serverIp),serverPort);
            socket.send(requestPacket);
            // 3、尝试获取服务器的响应数据，获取失败则进入阻塞
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            // 4、把获取到的响应数据转换成字符串并输出
            String response = new String(responsePacket.getData(),0,responsePacket.getLength());
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient udpEchoClient = new UdpEchoClient("127.0.0.1",9090);
        udpEchoClient.start();
    }
}

package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @BelongsProject: test-20240208
 * @BelongsPackage: network
 * @CreateTime : 2024/2/8 19:51
 * @Description: 回显服务器客户端
 * @Author: code_hlb
 */
public class UdpEchoClient {
    private DatagramSocket socket = null;
    private String serverIp;
    private int serverPort;
    public UdpEchoClient(String Ip,int Port) throws SocketException {
        // 客户端需要系统自动分配端口
        socket = new DatagramSocket();
        this.serverIp = Ip;
        this.serverPort = Port;
    }
    public void start() throws IOException {
        System.out.println("客户端启动！");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("-> ");
            // 1、发送请求
            String request = scanner.next();
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),0,request.getBytes().length,
                    InetAddress.getByName(serverIp),serverPort);
            socket.send(requestPacket);
            // 2、获取响应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            // 3、打印日志
            // System.out.println(request);
            // 针对翻译程序返回服务器响应结果
            String response = new String(responsePacket.getData(),0,responsePacket.getLength());
            System.out.println(response);
        }
    }
    public static void main(String[] args) throws IOException {
        /*UdpEchoClient udpEchoClient  = new UdpEchoClient("127.0.0.1",9090);
        udpEchoClient.start();*/
        UdpEchoClient udpEchoClient = new UdpEchoClient("127.0.0.1",9099);
        udpEchoClient.start();
    }
}

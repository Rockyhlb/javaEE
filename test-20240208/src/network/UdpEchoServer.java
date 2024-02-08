package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @BelongsProject: test-20240208
 * @BelongsPackage: network
 * @CreateTime : 2024/2/8 19:50
 * @Description: 回显服务器端
 * @Author: code_hlb
 */
public class UdpEchoServer {
    private DatagramSocket socket = null;
    public UdpEchoServer(int port) throws SocketException {
        // 服务器端需手动分配端口
        socket = new DatagramSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器端启动！");
        while (true) {
            // 定义DatagramPacket,并且传入“输出型参数“
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            // 1、获取请求
            socket.receive(requestPacket);
            // 2、处理请求
            String request = new String(requestPacket.getData(),0,requestPacket.getLength());
            String response = processRequest(request);
            // 3、将响应发送给客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),0,
                    response.getBytes().length,requestPacket.getAddress(),requestPacket.getPort());
            socket.send(responsePacket);
            // 4、打印日志
            System.out.printf("[%s:%d] req=%s,resp=%s\n",requestPacket.getAddress(),requestPacket.getPort(),
                    request,response);
        }
    }
    protected String processRequest(String request) {
        return request;
    }
    public static void main(String[] args) throws IOException {
        UdpEchoServer udpEchoServer = new UdpEchoServer(9090);
        udpEchoServer.start();
    }
}

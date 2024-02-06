package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @BelongsProject: test-20240206
 * @BelongsPackage: network
 * @CreateTime : 2024/2/6 22:01
 * @Description: 实现"回显服务器"服务器端
 * @Author: code_hlb
 */
public class UdpEchoServer {
    // Socket 本质上也是一种特殊的文件，相当于对网卡的抽象,socket中写数据，就相当于通过网卡发送数据，反之则是接收数据
    private DatagramSocket socket = null;

    public UdpEchoServer(int port) throws SocketException {
        // 初始化socket，服务器端需要手动绑定端口，但是客户端由于端口不可控(每个应用程序都会占用端口)，因此需要系统自行分配
        socket = new DatagramSocket(port);
        // 让系统自动分配空闲端口
        // socket = new DatagramSocket();
    }

    public void start() throws IOException {
        // 启动服务器
        System.out.println("服务器端启动！");
        // 由于客户端请求具有不定时的特点，因此服务器往往需要一直保持工作
        while (true) {
            // 1、读取文件并解析
            //  由于UDP是面向数据报的，因此需要初始化DatagramPacket对象作为数据报,new byte[4086]作为输出型参数传入
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            // 当服务器端开始工作，但客户端请求还没来时，receive()开始阻塞
            socket.receive(requestPacket);
            //  由于socket接收到的是二进制文件，因此我们还需要转换成字符串才能输出
            String request = new String(requestPacket.getData(),0,requestPacket.getLength());
            // 2、根据请求计算响应(最核心的操作!!)，此处由于是回显服务器就原样返回
            String response = process(request);
            // 3、把响应写回到客户端
            //  新new一个DatagramPacket对象并构造刚计算得出的响应数据(二进制传输)，再通过send返回
            //  response.getBytes().length 获取的是字节长度，如果改为 response.length()则获取到的就是字符长度了
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,
                    requestPacket.getSocketAddress()); // 同时指定目的地址
            socket.send(responsePacket); //send 内部对 输出型参数responsePacket进行填充
            // 打印日志，记录此次交互的信息
            System.out.printf("[%s:%d] req=%s,resp=%s\n",requestPacket.getAddress().toString(),
                    requestPacket.getPort(),request,response);

            // 此处的socket并不需要进行关闭，也不会发送文件资源泄露的原因：
            // socket在整个程序运行过程中都会被使用到，当socket不被使用时，那么就说明该进程结束了，也就说明PCB被销毁了，
            // 也就说明文件描述符表也随之销毁
        }
    }
    private static String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer udpEchoServer = new UdpEchoServer(9090);
        udpEchoServer.start();
    }
}

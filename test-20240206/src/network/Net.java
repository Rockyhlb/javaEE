package network;

/**
 * @BelongsProject: test-20240206
 * @BelongsPackage: network
 * @CreateTime : 2024/2/6 22:16
 * @Description: TODO
 * @Author: code_hlb
 */
public class Net {
    public static void main(String[] args) {
        // 网络,IP,端口,协议,五元组,协议分层,OSI七层模型,
        // TCP/IP 五层协议：
        // 应用层：对应到应用程序
        // 网络层：进行路径规划
        // 传输层：关注起点和终点
        // 数据链路层：两个相邻节点之间的数据传输情况
        // 物理层：描述的是网络通信的硬件设备
        // 对于一台主机，它的操作系统内核实现了从传输层到物理层的内容(五层都会涉及到)
        // 对于一台路由器，它实现了从网络层到物理层
        // 对于一台交换机，它实现了从数据链路层到物理层
        // 对于集线器，它只实现了物理层

        // 操作系统给我们提供了网络编程的API：Socket，API可以认为是应用层和传输层之间交互的路径,提供这套Socket API就可完成不同主机/系统之间的通信
        // 传输层，提供了的协议，主要是TCP/UDP
        // TCP是有连接的，UDP是无连接的
        // TCP是可靠传输的，UDP是不可靠传输的
        // TCP是可靠传输的(可靠传输机制)，UDP是不可靠传输的
        // TCP是面向字节流的，UDP是面向数据报的(网络通信数据的基本单位)
        // TCP和UDP都是全双工的(双向通信)
    }
}

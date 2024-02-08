package network;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: test-20240208
 * @BelongsPackage: network
 * @CreateTime : 2024/2/8 20:43
 * @Description: 基于之前写的”回显服务器“ 实现一个翻译服务器
 * @Author: code_hlb
 */
public class UdpDictServer extends UdpEchoServer{
    private Map<String,String> dictMap = new HashMap<>();
    public UdpDictServer(int port) throws SocketException {
        super(port);
        // 翻译，本质上就是查表
        dictMap.put("cat","小猫");
        dictMap.put("dog","小狗");
        dictMap.put("mouse","老鼠");
    }
    // 重写 start() 调用的processRequest()方法,实现翻译的逻辑，此处应用了多态的特性
    @Override
    protected String processRequest(String request) {
        return dictMap.getOrDefault(request,"该单词未被收录入词典！");
    }
    public static void main(String[] args) throws IOException {
        UdpEchoServer udpDictServer = new UdpDictServer(9099);
        udpDictServer.start();
    }
}

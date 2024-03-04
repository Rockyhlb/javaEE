import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @BelongsProject: message_wall
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/3/4 18:42
 * @Description: TODO
 * @Author: code_hlb
 */
@WebServlet("/message")
public class message_servlet extends HttpServlet {
    // 此处我们约定前后端数据交互的格式为json:{{"from":"zhangsan","to":lisi,"message":"我喜欢你!"}}
    private ObjectMapper objectMapper = new ObjectMapper();
    // 引入数据库存储数据，就不需要本地链表存储数据了
    // private List<Message> messageList = new LinkedList<>();
    private DataSource dataSource = new MysqlDataSource();
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1、创建数据源
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/message_wall?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("000000");
    }
    // 获取页面和页面记载时都会给服务器端发起Get请求
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 此处get请求不需要解析参数，直接返回数据即可
        resp.setStatus(200);
        resp.setContentType("application/json; charset=utf8");
        List<Message> messageList = new LinkedList<>();
        try {
            messageList = load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // jackson本身支持把List类型转换成json数组
        String messageJson = objectMapper.writeValueAsString(messageList);
        // 返回响应
        resp.getWriter().write(messageJson);
    }
    // 当前端点击“提交”按钮时，此时触发Post请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解析响应中的json数据
        Message message = objectMapper.readValue(req.getInputStream(),Message.class);
        System.out.println("请求中的message: " + message);
        // messageList.add(message);
        // 引入数据库存储数据
        try {
            save(message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // resp.setContentType("application/json; charset=utf8");
        // resp.getWriter().write("{ok: true}");
        resp.setStatus(200);
        resp.getWriter().write("ok");
    }
    private void save(Message message) throws SQLException {
        // 2、获取连接
        Connection connection = dataSource.getConnection();
        // 3、构造sql
        String sql = "insert into message values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,message.from);
        preparedStatement.setString(2,message.to);
        preparedStatement.setString(3,message.message);
        // 4、执行sql
        preparedStatement.executeUpdate();
        // 5、回收资源
        preparedStatement.close();
        connection.close();
    }
    private List<Message> load() throws SQLException {
        // 2、获取连接
        Connection connection = dataSource.getConnection();
        // 3、构造sql
        String sql = "select * from message";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 4、执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        // 5、遍历结果集
        List<Message> messageList = new LinkedList<>();
        while (resultSet.next()) {
            Message message = new Message();
            message.from = resultSet.getString("from");
            message.to = resultSet.getString("to");
            message.message = resultSet.getString("message");
            messageList.add(message);
        }
        // 5、回收资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
        // 6、返回结果
        return messageList;
    }
}

class Message {
    public String from;
    public String to;
    public String message;
    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: test-20240303
 * @BelongsPackage: servlet
 * @CreateTime : 2024/3/3 17:11
 * @Description:
 * @Author: code_hlb
 */
@WebServlet("/json")
public class JsonParameterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 此处约定请求格式如下:
        // POST /json
        // Content-Type: application/json
        //
        // {
        //    username: "zhangsan",
        //    password: "0000"
        // }

        // 此处也约定响应的格式(也按照 json 来组织):
        // {
        //     ok: true
        // }

        // 把请求的 body 按照 json 格式解析成 java 对象
        ObjectMapper objectMapper = new ObjectMapper();
        // getInputStream 获取请求的body，readValue通过反射创建出Request的实例，最终完成json -> java对象的映射
        Request request = objectMapper.readValue(req.getInputStream(),Request.class);
        System.out.println("userName: " + request.userName);
        System.out.println("passWord: " + request.passWord);

        Response response = new Response();
        response.OK = true;
        // 把响应对象转换成 json 字符串
        String respJson = objectMapper.writeValueAsString(response);
        resp.setContentType("application/json; charset=utf8");
        resp.getWriter().write(respJson);
    }
}
class Request {
    public String userName;
    public String passWord;
}
class Response {
    public boolean OK;
}

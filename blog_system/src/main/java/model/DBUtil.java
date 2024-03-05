package model;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @BelongsProject: blog_system
 * @BelongsPackage: model
 * @CreateTime : 2024/3/5 9:36
 * @Description: 数据库建立连接的操作
 * @Author: code_hlb
 */
public class DBUtil {
    // 由于可能会出现多个Servlet使用数据库的操作，因此此处的DataSource进行封装，
    // 同时还需加上单例模式
    private volatile static DataSource dataSource = null;
    // 1、获取数据源
    private static DataSource getDataSource() {
        // 懒汉模式下的线程安全
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/blog_system?characterEncoding=utf8&useSSL=false");
                    ((MysqlDataSource)dataSource).setUser("root");
                    ((MysqlDataSource)dataSource).setPassword("000000");
                }
            }
        }
        return dataSource;
    }
    // 2、获取连接
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    // 3、断开连接
    public static void close(Connection connection,PreparedStatement statement,ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

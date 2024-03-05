package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @BelongsProject: blog_system
 * @BelongsPackage: model
 * @CreateTime : 2024/3/5 9:35
 * @Description: 对Use表的数据操作
 * @Author: code_hlb
 */
public class UserDao {
    // 根据用户Id查询用户信息(获取用户信息)
    public User getUserById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from user where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassWord(resultSet.getString("passWord"));
                return user;
            }
        }catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            DBUtil.close(connection,statement,null);
        }
        return null;
    }
    // 根据用户name获取查询用户信息(登陆)
    public User getUserByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from user where userName = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassWord(resultSet.getString("passWord"));
                return user;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
        return null;
    }
}

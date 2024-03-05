package model;

/**
 * @BelongsProject: blog_system
 * @BelongsPackage: model
 * @CreateTime : 2024/3/5 9:22
 * @Description: User对应数据库中user表的一条记录
 * @Author: code_hlb
 */
public class User {
    private int id;
    private String userName;
    private String passWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}

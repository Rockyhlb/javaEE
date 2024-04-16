package com.hlb.principle.scope;

/**
 * @BelongsProject: test-20240416
 * @BelongsPackage: com.hlb.principle
 * @CreateTime : 2024/4/16 18:09
 * @Description: TODO
 * @Author: code_hlb
 */
public class User {
    private String userName;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}

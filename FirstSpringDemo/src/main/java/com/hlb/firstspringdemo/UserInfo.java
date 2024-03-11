package com.hlb.firstspringdemo;

/**
 * @BelongsProject: FirstSpringDemo
 * @BelongsPackage: com.hlb.firstspringdemo
 * @CreateTime : 2024/3/10 21:27
 * @Description: TODO
 * @Author: code_hlb
 */
public class UserInfo {
    private int id;
    private String name;
    private Integer age;

    public UserInfo() {
    }

    public UserInfo(int id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

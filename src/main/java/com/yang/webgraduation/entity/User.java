package com.yang.webgraduation.entity;


import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;
    private String username;
    private String password;
    private String phone;
    private String address;
    private Integer goldNum;
    private Integer level;

    public User(){

    }

    public User(String userid, String username, String password, String phone,String address) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(Integer goldNum) {
        this.goldNum = goldNum;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
package com.yang.webgraduation.dto;

public class SqlParams {
    //用户姓名
    String userName;
    //新增金币数
    Integer goldNum;

    public SqlParams(String userName, Integer goldNum) {
        this.userName = userName;
        this.goldNum = goldNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(Integer goldNum) {
        this.goldNum = goldNum;
    }
}

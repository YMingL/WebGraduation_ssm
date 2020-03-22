package com.yang.webgraduation.service;

import com.yang.webgraduation.dto.SqlParams;
import com.yang.webgraduation.entity.User;

/**
 * Created by acer on 2019-12-23.
 */
public interface UserService {

    /**
     * 获取用户信息
     */
    User getUserById(String userId);

    User getUserByName(String userName);

    /*
    * 添加用户 注册
    * */
    Integer addUser(User user);

    /*
    * 更新用户信息
    * */
    Integer updateUser(User user);

    /**
     * 注销用户
     * @param id
     * @return
     */
    Integer deleteUser(String id);

    /**
     * 更新用户金币数
     * @param sqlParams
     * @return
     */
    Integer updateUserGold(SqlParams sqlParams);

    /**
     * 获取最大的用户Id
     * @return
     */
    Integer getMaxUserId();
}

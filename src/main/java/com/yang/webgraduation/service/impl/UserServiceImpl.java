package com.yang.webgraduation.service.impl;

import com.yang.webgraduation.dao.UserMapper;
import com.yang.webgraduation.dto.SqlParams;
import com.yang.webgraduation.entity.User;
import com.yang.webgraduation.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by acer on 2019-12-23.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public User getUserById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.selectByName(userName);
    }

    @Override
    @Transactional(timeout = 10)
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    @Transactional(timeout = 10)
    public Integer updateUser(User user){
        return userMapper.updateById(user);
    }

    @Override
    public Integer deleteUser(String id) {
        return userMapper.deleteById(id);
    }

    @Override
    public Integer updateUserGold(SqlParams sqlParams) {
        return userMapper.updateUserGold(sqlParams);
    }

    @Override
    public Integer getMaxUserId(){
        String maxId = userMapper.selectMaxId();
        return Integer.parseInt(maxId);
    }
}


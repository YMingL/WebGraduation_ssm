package com.yang.webgraduation.dao;

import com.yang.webgraduation.dto.SqlParams;
import com.yang.webgraduation.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 方法后补加抛异常
 */
@Mapper
public interface UserMapper {

    int deleteById(String userid);

    int insert(User record);

    User selectById(String userid);

    int updateById(User record);

    User selectByName(String userName);

    int getUserGold(String userName);

    int updateUserGold(SqlParams sqlParams);

    @Select("select Max(userId) from user")
    String selectMaxId();
}
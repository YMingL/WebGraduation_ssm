package com.yang.webgraduation.dao;

import com.yang.webgraduation.entity.GoodsBrand;

import org.apache.ibatis.annotations.Mapper;
/**
 * 方法后补加抛异常
 */
@Mapper
public interface GoodsBrandMapper {

    int deleteById(Integer id);

    int insert(GoodsBrand record);


//    int insertSelective(GoodsBrand record);

    GoodsBrand selectById(Integer id);

//    int updateByPrimaryKeySelective(GoodsBrand record);
//
//    int updateByPrimaryKey(GoodsBrand record);
}
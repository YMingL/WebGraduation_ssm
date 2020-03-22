package com.yang.webgraduation.dao;

import com.yang.webgraduation.entity.ZdBrand;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 方法后补加抛异常
 */
@Mapper
public interface ZdBrandMapper {

    int deleteById(Integer brandid);

    int insert(ZdBrand record);

    ZdBrand selectById(Integer brandid);
}
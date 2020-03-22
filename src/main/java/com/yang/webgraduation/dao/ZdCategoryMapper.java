package com.yang.webgraduation.dao;

import com.yang.webgraduation.entity.ZdCategory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 方法后补加抛异常
 */
@Mapper
public interface ZdCategoryMapper {

    int deleteById(Integer categoryid);

    int deleteByListId(ArrayList<Integer> categoryList);

    int insert(ZdCategory record);

    ZdCategory selectById(Integer categoryid);

    int updateById(ZdCategory record);

    List<ZdCategory> selectCategoryList();
}
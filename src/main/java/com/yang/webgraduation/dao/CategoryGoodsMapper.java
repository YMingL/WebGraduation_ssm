package com.yang.webgraduation.dao;

import com.yang.webgraduation.entity.CategoryGoods;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * 方法后补加抛异常
 */
@Mapper
public interface CategoryGoodsMapper {


    /*
    * 根据商品id删除商品分类名
    * */
    int deleteById(Integer id);

    /*
    * 插入商品分类名
    * */
    int insert(CategoryGoods record);

//    int insertSelective(CategoryGoods record);

    /*
    * 根据商品id查询商品分类名
    * */
    CategoryGoods selectById(Integer id);



//    int updateByPrimaryKeySelective(CategoryGoods record);

//    int updateByPrimaryKey(CategoryGoods record);
}
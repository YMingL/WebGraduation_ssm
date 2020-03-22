package com.yang.webgraduation.dao;

import com.yang.webgraduation.entity.GoodsDetail;

import org.apache.ibatis.annotations.Mapper;
/**
 * 方法后补加抛异常
 */
@Mapper
public interface GoodsDetailMapper {

    /*
    * 根据id删除商品
    * */
    int deleteById(Integer id);

    /*
    * 插入商品
    * */
    int insert(GoodsDetail record);

    /*
    * 插入商品信息（有值的部分）
    * */
    int insertSelective(GoodsDetail record);

    /*
    * 根据商品id查询商品
    * */
    GoodsDetail selectByPrimaryKey(Integer id);


//    int updateByPrimaryKeySelective(GoodsDetail record);
    /*
    * 根据商品id更新商品信息
    * */
    int updateById(GoodsDetail record);
}
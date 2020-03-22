package com.yang.webgraduation.dao;

import com.yang.webgraduation.entity.Goods;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * 方法后补加抛异常
 */
@Mapper
public interface GoodsMapper {

    //管理员权限
    int deleteById(Integer goodsid);
    //管理员权限
    int insert(Goods record);
    //管理员权限
    int insertSelective(Goods record);

    /*
    *
    * 根据id查询
    * */
    Goods selectById(Integer goodsid);

    /*
    * 根据商品名查询
    * 默认排序
    * */
    List<Goods> selectByGoodsName(String goodsName);

    /*
    * 根据销量排序
    * 仍然根据商品名查询
    * */
    List<Goods> selectOrderBySales(String goodsName);

    /*
    * 分页
    * */
    List<Goods> selectListPage(@Param(value = "goodsName") String goodsName,
                               @Param(value = "pageStart") Integer start,
                               @Param(value = "pageSize") Integer size);

    //管理员权限
    int updateByPrimaryKeySelective(Goods record);
    //管理员权限
    int updateById(Goods record);

    @Select("select count(*) from goods where goodsName like concat('%',#{goodsName},'%')")
    int getGoodsNum(@Param("goodsName") String goodsName);


}
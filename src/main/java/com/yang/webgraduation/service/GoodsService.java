package com.yang.webgraduation.service;

import com.yang.webgraduation.entity.Goods;

import java.util.List;

/**
 * Created by acer on 2019-12-08.
 */
public interface GoodsService {

    /**
     *获得商品列表 根据商品名查询
     *
     */
    List<Goods> getListGoods(String goodsName);

    /**
     * 根据销量排序
     */
    List<Goods> getListOrderBySales(String goodsName);

    /**
     * 实现分页显示
     * @param pageNum  页码
     * @param pageSize 一页显示数量
     * @param goodsName 查询条件
     * @return 商品列表
     */
    List<Goods> getPageList(Integer pageNum,Integer pageSize, String goodsName);

    /**
     * 获取相关商品总数
     * @param goodsName
     * @return
     */
    int getGoodsNum(String goodsName);

}

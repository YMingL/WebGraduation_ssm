package com.yang.webgraduation.service;

/**
 * Created by acer on 2019-12-08.
 */

import com.yang.webgraduation.entity.CategoryGoods;

import java.util.List;

/**
 * 管理员功能 增减分类
 */
public interface CategoryGoodsService {


    /**
     * 添加分类
     * @return
     */
    Integer addCategory();

    /**
     * 删除分类
     * @return
     */
    Integer deleteCategory();

    /*
    * 分类级别  暂无
    * */
}


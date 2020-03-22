package com.yang.webgraduation.entity;

import java.io.Serializable;

/**
 * 商品分类
 */
public class CategoryGoods implements Serializable {

    private Integer id; //表主键
    private Integer goodsid; //商品id
    private Integer categoryid;//分类号


    public CategoryGoods(){

    }
    public CategoryGoods(Integer id, Integer goodsid, Integer categoryid) {
        this.id = id;
        this.goodsid = goodsid;
        this.categoryid = categoryid;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }
}
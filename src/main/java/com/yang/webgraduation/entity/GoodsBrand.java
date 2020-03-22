package com.yang.webgraduation.entity;

import java.io.Serializable;

/**
 *
 * 商品品牌
 */

public class GoodsBrand implements Serializable {

    private Integer id; //主键
    private Integer goodsid; //商品id
    private Integer brandid;//品牌id

    public GoodsBrand(){

    }
    public GoodsBrand(Integer id, Integer goodsid, Integer brandid) {
        this.id = id;
        this.goodsid = goodsid;
        this.brandid = brandid;
    }


    public Integer getId() {
        return id;
    }


    public Integer getGoodsid() {
        return goodsid;
    }


    public Integer getBrandid() {
        return brandid;
    }
}
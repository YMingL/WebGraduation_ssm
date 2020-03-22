package com.yang.webgraduation.entity;

import java.io.Serializable;

/**
 *
 * 商品明细
 */

public class GoodsDetail implements Serializable {

    private Integer id;//主键
    private Integer goodsid;
    private Integer sales; //销量
    private String shopname; //店铺名
    private String province; // 店铺所在省
    private String address; // 详细地址
    private String goodsImageUrl; //图片地址

    public GoodsDetail(){

    }

    public GoodsDetail(Integer id, Integer goodsid, Integer sales, String shopname, String province, String address,String goodsImageUrl) {
        this.id = id;
        this.goodsid = goodsid;
        this.sales = sales;
        this.shopname = shopname;
        this.province = province;
        this.address = address;
        this.goodsImageUrl = goodsImageUrl;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGoodsid() {
        return goodsid;
    }


    public Integer getSales() {
        return sales;
    }

    public String getShopname() {
        return shopname;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getGoodsImageUrl(){
        return goodsImageUrl;
    }
}
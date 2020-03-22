package com.yang.webgraduation.entity;

import java.io.Serializable;

/**
 *
 * 商品类
 */
public class Goods implements Serializable {

    private Integer goodsid;//商品id 主键
    private String goodsurl;//商品地址
    private String goodsname;//商品名
    private String origprice;//原价
    private String currprice;//现价

    private GoodsDetail goodsDetail;

    public Goods(){

    }
    public Goods(Integer goodsid, String goodsurl, String goodsname, String origprice, String currprice) {
        this.goodsid = goodsid;
        this.goodsurl = goodsurl;
        this.goodsname = goodsname;
        this.origprice = origprice;
        this.currprice = currprice;
    }

    public Goods(Integer goodsid, String goodsurl, String goodsname, String origprice, String currprice,GoodsDetail goodsDetail) {
        this.goodsid = goodsid;
        this.goodsurl = goodsurl;
        this.goodsname = goodsname;
        this.origprice = origprice;
        this.currprice = currprice;
        this.goodsDetail = goodsDetail;
    }
    public Integer getGoodsid() {
        return goodsid;
    }


    public String getGoodsurl() {
        return goodsurl;
    }


    public String getGoodsname() {
        return goodsname;
    }


    public String getOrigprice() {
        return origprice;
    }


    public String getCurrprice() {
        return currprice;
    }

    public GoodsDetail getGoodsDetail(){
        return goodsDetail;
    }
}
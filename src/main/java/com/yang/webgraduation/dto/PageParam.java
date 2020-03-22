package com.yang.webgraduation.dto;

/**
 * Created by acer on 2019-12-22.
 */
public class PageParam {

    private String goodsName;
    private Integer pageStart;
    private Integer pageSize;

    public PageParam(String goodsName,Integer pageStart,Integer pageSize){
        this.goodsName = goodsName;
        this.pageStart = pageStart;
        this.pageSize = pageSize;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

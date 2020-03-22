package com.yang.webgraduation.entity;

import java.io.Serializable;

/**
 *
 * 品牌字典
 */
public class ZdBrand implements Serializable {

    private Integer brandid; //品牌id
    private String brandname;//品牌名

    public ZdBrand(){

    }
    public ZdBrand(Integer brandid, String brandname) {
        this.brandid = brandid;
        this.brandname = brandname;
    }

    public Integer getBrandid() {
        return brandid;
    }


    public String getBrandname() {
        return brandname;
    }
}
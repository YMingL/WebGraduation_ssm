package com.yang.webgraduation.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 分类号字典
 */

public class ZdCategory implements Serializable {

    private Integer categoryid; //分类id
    private String categoryname;//分类名
    private Integer categoryLevel; //分类级别 为1代表一级目录
    private Integer categoryGroup; //分类组别，值为分类id，代表属于哪一大类，若为0则表示无父分类
    private List<ZdCategory> categoryList; //子分类

    public List<ZdCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ZdCategory> categoryList) {
        this.categoryList = categoryList;
    }


    public ZdCategory(){

    }

    public ZdCategory(Integer categoryid, String categoryname, Integer categoryLevel, Integer categoryGroup) {
        this.categoryid = categoryid;
        this.categoryname = categoryname;
        this.categoryLevel = categoryLevel;
        this.categoryGroup = categoryGroup;
    }

    public ZdCategory(Integer categoryid, String categoryname, Integer categoryLevel, Integer categoryGroup,List<ZdCategory> categoryLista) {
        this.categoryid = categoryid;
        this.categoryname = categoryname;
        this.categoryLevel = categoryLevel;
        this.categoryGroup = categoryGroup;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Integer getCategoryGroup() {
        return categoryGroup;
    }

    public void setCategoryGroup(Integer categoryGroup) {
        this.categoryGroup = categoryGroup;
    }
}
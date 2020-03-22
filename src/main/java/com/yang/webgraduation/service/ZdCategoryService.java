package com.yang.webgraduation.service;

import com.yang.webgraduation.entity.ZdCategory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface ZdCategoryService {

    /**
     * 删除单条分类
     * @param categoryId
     * @return
     */
    Integer deleteCategory(Integer categoryId);

    /**
     * 批量删除分类
     * @param categoryList
     * @return
     */
    Integer deleteCategoryList(ArrayList<Integer> categoryList);

    /**
     * 查找所有分类
     * @return
     */
    List<ZdCategory> getCategoryList();

    /**
     * 添加新的分类
     * @param Category
     * @return
     */
    Integer addCategory(ZdCategory Category);
}

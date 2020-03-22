package com.yang.webgraduation.service.impl;


import com.yang.webgraduation.dao.ZdCategoryMapper;
import com.yang.webgraduation.entity.ZdCategory;
import com.yang.webgraduation.service.ZdCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZdCategoryServiceImpl implements ZdCategoryService {
    @Autowired
    private ZdCategoryMapper zdCategoryMapper;
    @Override
    public Integer deleteCategory(Integer categoryId) {
        return zdCategoryMapper.deleteById(categoryId);
    }

    /**
     * @param categoryList
     * @return
     */
    @Override
    public Integer deleteCategoryList(ArrayList<Integer> categoryList) {
        return zdCategoryMapper.deleteByListId(categoryList);
    }

    @Override
    public List<ZdCategory> getCategoryList() {
        return null;
    }

    @Override
    public Integer addCategory(ZdCategory Category) {
        return null;
    }
}

package com.yang.webgraduation.service.impl;

import com.sun.xml.bind.annotation.OverrideAnnotationOf;
import com.yang.webgraduation.dao.GoodsMapper;
import com.yang.webgraduation.entity.Goods;
import com.yang.webgraduation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by acer on 2019-12-22.
 */

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getListGoods(String goodsName) {
        return goodsMapper.selectByGoodsName(goodsName);
    }

    @Override
    public List<Goods> getListOrderBySales(String goodsName) {
        return goodsMapper.selectOrderBySales(goodsName);
    }

//    @Cacheable(value = "pageList",key = "'goods_'+#goodsName+'_'+#pageNum")
    @Override
    public List<Goods> getPageList(Integer pageNum,Integer pageSize, String goodsName){
        //计算取数据范围
        int pageStart = (pageNum-1)*pageSize + 1; //页面第一条数据
        return goodsMapper.selectListPage(goodsName,pageStart-1,pageSize);
    }

    @Override
    public int getGoodsNum(String goodsName) {
        return goodsMapper.getGoodsNum(goodsName);
    }
}



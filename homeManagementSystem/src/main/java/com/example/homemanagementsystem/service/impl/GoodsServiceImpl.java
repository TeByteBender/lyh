package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.GoodsMapper;
import com.example.homemanagementsystem.pojo.ConsumerUser;
import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> listByKind(Integer id) {
        return goodsMapper.getByKindsId(id);
    }

    @Override
    public Goods getGoodsInfo(Integer id) {
        return goodsMapper.getById(id);
    }

    @Override
    public PageBean getAllGoods(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Goods> goods = goodsMapper.getAllGoods();
        Page<Goods> p = (Page<Goods>) goods;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void removeGoods(List<Integer> ids) {
        goodsMapper.deleteGoods(ids);
    }

    @Override
    public void uploadImage(Integer id, String url) {
        goodsMapper.updateImage(id, url);
    }
}

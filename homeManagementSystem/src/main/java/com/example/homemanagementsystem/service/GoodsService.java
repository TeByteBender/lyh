package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.PageBean;

import java.util.List;

public interface GoodsService {
    /**
     * 按分类展示服务
     * @param id 分类id
     * @return 服务列表
     */
    List<Goods> listByKind(Integer id);

    /**
     * 获取商品信息
     * @param id 商品id
     * @return 商品对象
     */
    Goods getGoodsInfo(Integer id);

    /**
     * 上传图片
     * @param id 商品id
     * @param url 图片url
     */
    void uploadImage(Integer id, String url);

    /**
     * 分页查询所有商品信息
     * @param page 页码
     * @param pageSize 分页大小
     * @return
     */
    PageBean getAllGoods(Integer page, Integer pageSize);

    /**
     * 删除商品
     * @param ids 商品id
     * @return Result
     */
    void removeGoods(List<Integer> ids);
}

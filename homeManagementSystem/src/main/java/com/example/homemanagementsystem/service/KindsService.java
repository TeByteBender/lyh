package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.Kinds;
import com.example.homemanagementsystem.pojo.PageBean;

import java.util.List;

public interface KindsService {
    /**
     * 查询所有种类
     * @return 种类列表
     */
    List<Kinds> listAll();

    /**
     * 分页查询所有商品种类信息
     * @param page 页码
     * @param pageSize 每页展示的条数
     * @return
     */
    PageBean getAllKinds(Integer page, Integer pageSize);

    /**
     * 删除商品种类
     * @param ids 商品种类id
     * @return Result
     */
    void deleteKinds(List<Integer> ids);
}

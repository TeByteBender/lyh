package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.pojo.Worker;

import java.util.List;

public interface WorkerService {
    /**
     * 查看所有空闲的家政人员
     * @return 家政人员列表
     */
    List<Worker> browseLeisureWorker();

    /**
     * 登录
     * @param worker 家政人员
     * @return
     */
    Worker login(Worker worker);

    /**
     * 获取用户信息
     * @param id 用户id
     * @return 家政人员对象
     */
    Worker getUserInfo(Integer id);

    /**
     * 分页查询所有家政人员信息
     * @param page 页码
     * @param pageSize 每页展示的条数
     * @return
     */
    PageBean getAllWorker(Integer page, Integer pageSize);

    /**
     * 上传头像
     * @param id 用户id
     * @param url 图片url
     */
    void uploadImage(Integer id, String url);

    /**
     * 取现
     * @param id 家政人员id
     * @param password 密码
     * @param money 取现金额
     * @return Result
     */
    Result withdrawCash(Integer id, String password, Double money);

    /**
     * 家政人员注册
     * @param worker 家政人员
     * @return Result
     */
    Result register(Worker worker);

    /**
     * 删除家政人员
     * @param ids 家政人员id
     */
    void removeWorker(List<Integer> ids);
}

package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.WorkerMapper;
import com.example.homemanagementsystem.pojo.*;
import com.example.homemanagementsystem.service.WorkerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public List<Worker> browseLeisureWorker() {
        return workerMapper.getWorkerByStatus();
    }

    @Override
    public Worker login(Worker worker) {
        return workerMapper.getWorkerByUsernameAndPassword(worker);
    }

    @Override
    public Worker getUserInfo(Integer id) {
        return workerMapper.getWorkerById(id);
    }

    @Override
    public PageBean getAllWorker(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Order> orders = workerMapper.selectAll();
        Page<Order> p = (Page<Order>) orders;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public void uploadImage(Integer id, String url) {
        workerMapper.updateImage(id, url);
    }

    @Override
    public Result withdrawCash(Integer id, String password, Double money) {
        // 获取家政人员信息
        Worker worker = workerMapper.getWorkerById(id);

        // 密码错误
        if (!password.equals(worker.getPassword())) {
            return Result.error("密码错误");
        }

        money = -money;

        // 更新家政人员余额
        workerMapper.updateMoney(id, money);
        return Result.success();
    }

    @Override
    public Result register(Worker worker) {
        String username = worker.getUsername().trim();
        String password = worker.getPassword().trim();

        worker.setImage("http://s5ylvlikx.hd-bkt.clouddn.com/avatar.jpg");

        if (username.equals("") || password.equals("")) {
            return Result.error("用户名密码不能为空");
        }

        // 通过用户名查找用户信息
        List<Worker> workers = workerMapper.getUserByName(username);

        if ((workers != null) && (workers.size() > 0)) {
            return Result.error("用户名已存在");
        }

        workerMapper.insert(worker);
        return Result.success("注册成功，请登录！");
    }

    @Override
    public void removeWorker(List<Integer> ids) {
        workerMapper.deleteById(ids);
    }
}

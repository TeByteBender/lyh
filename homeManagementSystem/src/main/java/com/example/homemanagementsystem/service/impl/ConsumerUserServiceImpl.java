package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.ConsumerUserMapper;
import com.example.homemanagementsystem.mapper.OrderMapper;
import com.example.homemanagementsystem.mapper.WorkerMapper;
import com.example.homemanagementsystem.pojo.ConsumerUser;
import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.service.ConsumerUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class ConsumerUserServiceImpl implements ConsumerUserService {

    @Autowired
    private ConsumerUserMapper consumerUserMapper;

    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ConsumerUser login(ConsumerUser consumerUser) {
        return consumerUserMapper.getConsumerUserNameAndPassWord(consumerUser);
    }

    @Override
    public PageBean getAllConsumerUser(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<ConsumerUser> consumerUsers = consumerUserMapper.getAllUser();
        Page<ConsumerUser> p = (Page<ConsumerUser>) consumerUsers;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public Result register(ConsumerUser consumerUser) {
        String username = consumerUser.getUsername().trim();
        String password = consumerUser.getPassword().trim();

        consumerUser.setImage("http://s5ylvlikx.hd-bkt.clouddn.com/avatar.jpg");

        if (username.equals("") || password.equals("")) {
            return Result.error("用户名密码不能为空");
        }

        // 通过用户名查找用户信息
        List<ConsumerUser> users = consumerUserMapper.getUserByName(username);

        if ((users != null) && (users.size() > 0)) {
            return Result.error("用户名已存在");
        }

        consumerUserMapper.insert(consumerUser);
        return Result.success("注册成功，请登录！");
    }

    @Override
    public ConsumerUser getUserInfo(Integer id) {
        return consumerUserMapper.getUserById(id);
    }

    @Override
    public ConsumerUser editUserInfo(ConsumerUser consumerUser) {
        // 修改个人信息
        consumerUserMapper.updateUser(consumerUser);
        // 查询个人信息
        return consumerUserMapper.getUserById(consumerUser.getId());
    }

    @Override
    public Result editPassword(Integer id, String password) {
        ConsumerUser consumerUser = consumerUserMapper.getUserById(id);

        // 如果原密码与新密码相同
        if (password.equals(consumerUser.getPassword())) {
            return Result.error("原密码与新密码相同");
        }

        consumerUserMapper.updatePassword(id, password);

        return Result.success();
    }

    @Override
    public ConsumerUser uploadImage(Integer id, String url) {
        consumerUserMapper.updateImage(id, url);
        return consumerUserMapper.getUserById(id);
    }

    @Override
    public Result topUp(Integer id, String password, String topUpMoney) {
        // 获取用户信息
        ConsumerUser consumerUser = consumerUserMapper.getUserById(id);

        // 若密码不正确
        if (!password.equals(consumerUser.getPassword())) {
            return Result.error("密码错误，请重新输入");
        }

        // String --> Double
        Double tum = Double.valueOf(topUpMoney);

        BigDecimal bg = new BigDecimal(tum);
        // 直接舍去多余的小数位
        double money = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        // 更新账户余额
        consumerUserMapper.updateMoney(id, money);
        return Result.success();
    }

    @Override
    @Transactional
    public Result pay(Order order) {

        int userId = order.getUserId(); // 用户id
        int workerId = order.getWorkerId(); // 家政人员id
        double goodPrice = Double.parseDouble(order.getGoodsPrice());   // 商品价格

        // 获取用户信息
        ConsumerUser consumerUser = consumerUserMapper.getUserById(userId);
        String password = consumerUser.getPassword();   // 用户密码
        double userMoney = consumerUser.getMoney(); // 用户余额

        // 若密码不正确
        if (!password.equals(consumerUser.getPassword())) {
            return Result.error("密码错误，请重新输入");
        } else if (userMoney < goodPrice) { // (余额 < 商品价格) --> 余额不足
            return Result.error("余额不足，请充值");
        }

        double payment = Double.parseDouble(order.getGoodsPrice());
        payment = -payment;

        // 更新消费者账户余额
        consumerUserMapper.updateMoney(userId, payment);
        // 更新家政人员账户余额
        workerMapper.updateMoney(workerId, goodPrice);
        // 更新订单状态为（1 已完成）
        orderMapper.updateStatus(1);

        return Result.success();
    }

//
//    @Override
//    public List<Worker> browseLeisureWorker() {
//        return consumerUserMapper.getWorkerByStatus();
//    }
}

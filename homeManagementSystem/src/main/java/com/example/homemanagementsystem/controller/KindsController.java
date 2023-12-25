package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.Kinds;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.service.KindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KindsController {

    @Autowired
    private KindsService kindsService;

    /**
     * 查询所有种类
     * @return 结果
     */
    @GetMapping("/kinds/listAll")
    public Result listAll() {
        List<Kinds> kinds = kindsService.listAll();
        return Result.success(kinds);
    }

    /**
     * 分页查询所有商品种类信息
     * @param page 页数
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping("/adminUser/getAllKinds")
    public Result getAllKinds(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = kindsService.getAllKinds(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 删除商品种类
     * @param ids 商品种类id
     * @return Result
     */
    @DeleteMapping("/adminUser/deleteKinds/{ids}")
    public Result deleteKinds(@PathVariable List<Integer> ids) {
        kindsService.deleteKinds(ids);

        return Result.success();
    }

}

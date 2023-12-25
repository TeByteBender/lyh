package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.pojo.Worker;
import com.example.homemanagementsystem.service.GoodsService;
import com.example.homemanagementsystem.utils.JwtUtils;
import com.example.homemanagementsystem.utils.QiniuKodoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private QiniuKodoUtils qiniuKodoUtils;

    /**
     * 按分类展示服务
     * @param id 分类id
     * @return 结果
     */
    @GetMapping("/goods/{id}")
    public Result listByKind(@PathVariable Integer id) {
        List<Goods> goods =  goodsService.listByKind(id);

        return Result.success(goods);
    }

    /**
     * 分页查询所有商品信息
     * @param page 页数
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping("/adminUser/getAllGoods")
    public Result getAllGoods(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = goodsService.getAllGoods(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 上传图片
     * @param image 图片
     * @return 结果
     * @throws IOException
     */
    @Transactional
    @PostMapping("/goods/upload")
    public Result upload(Integer id, MultipartFile image ,HttpServletRequest request) throws IOException {

        // 上传文件
        String url = qiniuKodoUtils.upload(image);

        // 获取请求头中的令牌（token）
        String jwt = request.getHeader("token");

        // 解析 token。
        try {
            Map<String, Object> claims = new HashMap<>();
            claims = JwtUtils.parseJWT(jwt);  // jwt 包含当前包含的员工信息
        } catch (Exception e) {
            e.printStackTrace();

            return Result.error("您还未登录");
        }

        // 获得商品信息
        Goods goods = goodsService.getGoodsInfo(id);
        String oldUrl = goods.getImage();

        // 修改商品的image信息
        goodsService.uploadImage(id, url);

        // 判断oldUrl是否为null或空
        if (!(oldUrl == null) && !oldUrl.equals("")){
            // 删除七牛云中原本的文件
            qiniuKodoUtils.delete(oldUrl);
        }

        return Result.success();
    }

    /**
     * 删除商品
     * @param ids 商品id
     * @return Result
     */
    @DeleteMapping("/adminUser/removeGoods/{ids}")
    public Result removeGoods(@PathVariable List<Integer> ids) {
        goodsService.removeGoods(ids);

        return Result.success();
    }
}

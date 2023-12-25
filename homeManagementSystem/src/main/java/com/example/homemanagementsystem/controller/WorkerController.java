package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.pojo.Worker;
import com.example.homemanagementsystem.service.WorkerService;
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
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private QiniuKodoUtils qiniuKodoUtils;

    /**
     * 查看所有空闲的家政人员
     * @return 家政人员列表
     */
    @GetMapping("/consumerUser/browseLeisureWorker")
    public Result browseLeisureWorker() {
        List<Worker> workers = workerService.browseLeisureWorker();

        return Result.success(workers);
    }

    /**
     * 分页查询所有家政人员信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return
     */
    @GetMapping("/adminUser/getAllWorker")
    public Result getAllWorker(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = workerService.getAllWorker(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 取现
     * @param id 家政人员id
     * @param money 提取金额
     * @return
     */
    @PutMapping("/worker/withdrawCash")
    public Result withdrawCash(Integer id, String password, Double money) {
        workerService.withdrawCash(id, password, money);
        return Result.success();
    }

    /**
     * 上传头像
     * @param image 图片
     * @return 结果
     * @throws IOException
     */
    @Transactional
    @PostMapping("/worker/upload")
    public Result upload(MultipartFile image, HttpServletRequest request) throws IOException {

        // 上传文件
        String url = qiniuKodoUtils.upload(image);

        // 获取请求头中的令牌（token）
        String jwt = request.getHeader("token");

        Integer id = null;

        // 解析 token。
        try {
            Map<String, Object> claims = new HashMap<>();
            claims = JwtUtils.parseJWT(jwt);  // jwt 包含当前包含的员工信息

            id = (Integer) claims.get("id");    // 得到用户id值
        } catch (Exception e) {
            e.printStackTrace();

            return Result.error("您还未登录");
        }

        // 获得用户信息
        Worker worker = workerService.getUserInfo(id);
        String oldUrl = worker.getImage();

        // 修改用户的image信息
        workerService.uploadImage(id, url);

        // 判断 oldUrl 是否为 null或空
        if (!(oldUrl == null) && !oldUrl.equals("")){
            // 删除七牛云中原本的文件
            qiniuKodoUtils.delete(oldUrl);
        }

        return Result.success();
    }

    /**
     * 删除家政人员
     * @param ids 家政人员id
     * @return Result
     */
    @DeleteMapping("/adminUser/removeWorker/{ids}")
    public Result removeWorker(@PathVariable List<Integer> ids) {

        workerService.removeWorker(ids);

        return Result.success();
    }
}

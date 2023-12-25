package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.AdminUser;
import com.example.homemanagementsystem.pojo.ConsumerUser;
import com.example.homemanagementsystem.pojo.Worker;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ConsumerUserTest {

    @Autowired
    private ConsumerUserMapper consumerUserMapper;


    @Test
    public void testUserLogin() {
        ConsumerUser consumerUser = new ConsumerUser();
        consumerUser.setUsername("yonghu");
        consumerUser.setPassword("123456");

        ConsumerUser user = consumerUserMapper.getConsumerUserNameAndPassWord(consumerUser);
        System.out.println(user);
    }

    @Test
    public void testGetUserInfo() {
        ConsumerUser consumerUser = consumerUserMapper.getUserById(1);

        System.out.println(consumerUser);
    }

    @Test
    public void testUpdateUser() {
        ConsumerUser consumerUser = new ConsumerUser();

        consumerUser.setId(3);
        consumerUser.setUsername("zoo");
        consumerUser.setName("张园园");
        consumerUser.setPassword("123456");
        consumerUser.setSex((short) 0);
        consumerUser.setAddress("至诚");
        consumerUser.setPhone("12345678910");
        consumerUser.setEmail("zoo@qq.com");

        consumerUserMapper.updateUser(consumerUser);
    }

    @Test
    public void testUpload() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本

        UploadManager uploadManager = new UploadManager(cfg);

        //...生成上传凭证，然后准备上传
        String accessKey = "zSI6BvjmdlusbC793Eq5JgKUtkxDUlEbJi1gQnMK";
        String secretKey = "bt5Upx_Tngo7TOCnnWvqlT-Dh9u6AM-LZaZBcl62";
        String bucket = "smallblack-home-management-system";

        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\l\\Desktop\\可爱晨1.jpg";

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);

                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
        }
    }
}

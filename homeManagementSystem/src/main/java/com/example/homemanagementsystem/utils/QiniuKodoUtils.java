package com.example.homemanagementsystem.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
public class QiniuKodoUtils {

    // 注入 QiniuProperties 的 bean 对象
    @Autowired
    private QiniuProperties qiniuProperties;

    /**
     * 实现上传图片的功能（使用数据流上传）
     * @param file 文件
     * @return 路径
     * @throws IOException
     */
    public String upload(MultipartFile file) throws IOException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);

        // 生成上传凭证，然后准备上传
        String endpoint = qiniuProperties.getEndpoint();
        String accessKey = qiniuProperties.getAccessKey();
        String secretKey = qiniuProperties.getSecretKey();
        String bucket = "smallblack-home-management-system";

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        try {
            byte[] uploadBytes = file.getBytes();
            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(byteInputStream,fileName,upToken,null, null);

                // 关闭输入流
                byteInputStream.close();

                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                System.out.println(putRet.key);
//                System.out.println(putRet.hash);
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
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }

        // 拼接文件访问的url
        String url = endpoint + "/" + fileName;

        return url;
    }

    /**
     * 删除七牛云kodo中的文件
     * @param url 文件路径
     */
    public void delete(String url) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());

        //...生成上传凭证，然后准备上传
        String endpoint = qiniuProperties.getEndpoint();
        String accessKey = qiniuProperties.getAccessKey();
        String secretKey = qiniuProperties.getSecretKey();
        String bucket = "smallblack-home-management-system";

        // 获得文件名
        String fileName = url.split("//")[1].split("/")[1];

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, fileName);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }

    }
}

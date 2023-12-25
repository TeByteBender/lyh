package com.example.homemanagementsystem.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "qiniu.kodo")
public class QiniuProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}

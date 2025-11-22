package com.kaiming.weblog.module.admin.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * ClassName: MinioProperties
 * Package: com.kaiming.weblog.module.admin.config
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 11:34
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "minio")
@Component
@Data
public class MinioProperties {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}

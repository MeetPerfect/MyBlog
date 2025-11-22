package com.kaiming.weblog.module.admin.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MinioConfig
 * Package: com.kaiming.weblog.module.admin.config
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 11:34
 * @Version 1.0
 */
@Configuration
public class MinioConfig {
    
    @Autowired
    private MinioProperties minioProperties;
    
    @Bean
    public MinioClient minioClient() {
        // 构建 Minio 客户端
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}

package com.kaiming.weblog.module.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MybatisPlusConfig
 * Package: com.kaiming.weblog.module.common.config
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/14 9:42
 * @Version 1.0
 */
@Configuration
@MapperScan("com.kaiming.weblog.module.common.domain.mapper")
public class MybatisPlusConfig {
}

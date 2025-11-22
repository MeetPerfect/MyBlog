package com.kaiming.weblog.module.common.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
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
    
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
    
    @Bean
    public InsertBatchSqlInjector insertBatchSqlInjector() {
        return new InsertBatchSqlInjector();
    }
}

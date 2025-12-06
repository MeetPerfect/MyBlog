package com.kaiming.weblog.search.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: LuceneProperties
 * Package: com.kaiming.weblog.search.runner.config
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 14:09
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "lucene")
@Component
@Data
public class LuceneProperties {

    /**
     * 索引存放的文件夹
     */
    private String indexDir;
}

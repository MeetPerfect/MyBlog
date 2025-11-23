package com.kaiming.weblog.module.common.domain.dos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * ClassName: ArticlePublishCountDO
 * Package: com.kaiming.weblog.module.common.domain.dos
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 11:07
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticlePublishCountDO {

    /**
     * 日期
     */
    private LocalDate date;

    /**
     * 文章发布数量
     */
    private Long count;
}

package com.kaiming.weblog.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FindStatisticsInfoRspVO
 * Package: com.kaiming.weblog.web.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 13:26
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindStatisticsInfoRspVO {

    /**
     * 文章总数
     */
    private Long articleTotalCount;

    /**
     * 分类总数
     */
    private Long categoryTotalCount;

    /**
     * 标签总数
     */
    private Long tagTotalCount;

    /**
     * 总浏览量
     */
    private Long pvTotalCount;
}

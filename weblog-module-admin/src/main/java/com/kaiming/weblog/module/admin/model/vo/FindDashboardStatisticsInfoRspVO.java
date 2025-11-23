package com.kaiming.weblog.module.admin.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FindDashboardStatisticsInfoRspVO
 * Package: com.kaiming.weblog.module.admin.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 10:57
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询仪表盘基础统计信息入参 VO")
public class FindDashboardStatisticsInfoRspVO {

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

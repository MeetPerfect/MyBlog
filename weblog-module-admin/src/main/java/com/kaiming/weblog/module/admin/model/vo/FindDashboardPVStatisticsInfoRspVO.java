package com.kaiming.weblog.module.admin.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: FindDashboardPVStatisticsInfoRspVO
 * Package: com.kaiming.weblog.module.admin.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 11:23
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询仪表盘文章 PV 访问量信息入参 VO")
public class FindDashboardPVStatisticsInfoRspVO {

    /**
     * 日期集合
     */
    private List<String> pvDates;

    /**
     * PV 浏览量集合
     */
    private List<Long> pvCounts;
}

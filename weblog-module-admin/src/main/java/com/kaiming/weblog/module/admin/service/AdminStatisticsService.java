package com.kaiming.weblog.module.admin.service;

/**
 * ClassName: AdminStatisticsService
 * Package: com.kaiming.weblog.module.admin.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 20:48
 * @Version 1.0
 */
public interface AdminStatisticsService {

    /**
     * 统计各分类下文章总数
     */
    void statisticsCategoryArticleTotal();

    /**
     * 统计各标签下文章总数
     */
    void statisticsTagArticleTotal();
}

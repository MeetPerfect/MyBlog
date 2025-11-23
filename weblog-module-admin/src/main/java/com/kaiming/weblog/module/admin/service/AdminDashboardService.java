package com.kaiming.weblog.module.admin.service;

import com.kaiming.weblog.module.common.utils.Response;

/**
 * ClassName: AdminDashboardService
 * Package: com.kaiming.weblog.module.admin.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 10:58
 * @Version 1.0
 */
public interface AdminDashboardService {

    /**
     * 获取仪表盘基础统计信息
     * @return
     */
    Response findDashboardStatistics();

    /**
     * 获取文章发布热点统计信息
     * @return
     */
    Response findDashboardPublishArticleStatistics();

    /**
     * 获取文章最近一周 PV 访问量统计信息
     * @return
     */
    Response findDashboardPVStatistics();
}

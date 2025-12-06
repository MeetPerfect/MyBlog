package com.kaiming.weblog.web.service;

import com.kaiming.weblog.module.common.utils.Response;

/**
 * ClassName: StatisticsService
 * Package: com.kaiming.weblog.web.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 13:26
 * @Version 1.0
 */
public interface StatisticsService {

    /**
     * 获取文章总数、分类总数、标签总数、总访问量统计信息
     * @return
     */
    Response findInfo();
}

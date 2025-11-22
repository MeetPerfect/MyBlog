package com.kaiming.weblog.web.service;

import com.kaiming.weblog.module.common.utils.Response;

/**
 * ClassName: BlogSettingsService
 * Package: com.kaiming.weblog.web.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 17:22
 * @Version 1.0
 */
public interface BlogSettingsService {

    /**
     * 获取博客设置信息
     * @return
     */
    Response findDetail();
}

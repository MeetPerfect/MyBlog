package com.kaiming.weblog.web.service;

import com.kaiming.weblog.module.common.utils.Response;

/**
 * ClassName: CategoryService
 * Package: com.kaiming.weblog.web.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 17:12
 * @Version 1.0
 */
public interface CategoryService {

    /**
     * 获取分类列表
     * @return
     */
    Response findCategoryList();
}

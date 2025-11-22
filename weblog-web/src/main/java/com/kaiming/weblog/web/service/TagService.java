package com.kaiming.weblog.web.service;

import com.kaiming.weblog.module.common.utils.Response;

/**
 * ClassName: TagService
 * Package: com.kaiming.weblog.web.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 17:15
 * @Version 1.0
 */
public interface TagService {

    /**
     * 查询标签列表
     *
     * @return
     */
    Response findTagList();
}

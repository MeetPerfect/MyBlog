package com.kaiming.weblog.web.service;

import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.model.vo.FindArchiveArticlePageListReqVO;

/**
 * ClassName: ArchiveService
 * Package: com.kaiming.weblog.web.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 20:33
 * @Version 1.0
 */
public interface ArchiveService {

    /**
     * 获取文章归档分页数据
     * @param findArchiveArticlePageListReqVO
     * @return
     */
    Response findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO);
}

package com.kaiming.weblog.web.service;

import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.model.vo.SearchArticlePageListReqVO;

/**
 * ClassName: SearchService
 * Package: com.kaiming.weblog.web.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 18:55
 * @Version 1.0
 */
public interface SearchService {

    /**
     * 关键词分页搜索
     * @param searchArticlePageListReqVO
     * @return
     */
    Response searchArticlePageList(SearchArticlePageListReqVO searchArticlePageListReqVO);
}

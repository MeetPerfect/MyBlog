package com.kaiming.weblog.web.service;

import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.model.vo.FindIndexArticlePageListReqVO;

/**
 * ClassName: ArticleService
 * Package: com.kaiming.weblog.web.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 16:00
 * @Version 1.0
 */
public interface ArticleService {

    /**
     * 获取首页文章分页数据
     * @param findIndexArticlePageListReqVO
     * @return
     */
    Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO);
}

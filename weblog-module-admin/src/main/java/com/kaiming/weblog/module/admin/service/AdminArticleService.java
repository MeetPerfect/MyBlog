package com.kaiming.weblog.module.admin.service;

import com.kaiming.weblog.module.admin.model.vo.DeleteArticleReqVO;
import com.kaiming.weblog.module.admin.model.vo.PublishArticleReqVO;
import com.kaiming.weblog.module.common.utils.Response;

/**
 * ClassName: AdminArticleService
 * Package: com.kaiming.weblog.module.admin.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 12:28
 * @Version 1.0
 */
public interface AdminArticleService {

    /**
     * 发布文章
     * @param publishArticleReqVO
     * @return
     */
    Response publishArticle(PublishArticleReqVO publishArticleReqVO);

    /**
     * 删除文章
     * @param deleteArticleReqVO
     * @return
     */
    Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO);
}

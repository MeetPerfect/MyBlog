package com.kaiming.weblog.web.service;

import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.model.vo.FindTagArticlePageListReqVO;

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

    /**
     * 获取标签下文章分页列表
     * @param findTagArticlePageListReqVO
     * @return
     */
    Response findTagPageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO);
}

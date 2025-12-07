package com.kaiming.weblog.module.admin.service;

import com.kaiming.weblog.module.admin.model.vo.AddWikiReqVO;
import com.kaiming.weblog.module.common.utils.Response;

/**
 * ClassName: AdminWikiService
 * Package: com.kaiming.weblog.module.admin.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/7 16:47
 * @Version 1.0
 */
public interface AdminWikiService {

    /**
     * 新增知识库
     * @param addWikiReqVO
     * @return
     */
    Response addWiki(AddWikiReqVO addWikiReqVO);
}

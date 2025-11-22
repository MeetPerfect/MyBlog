package com.kaiming.weblog.module.admin.service;

import com.kaiming.weblog.module.admin.model.vo.UpdateBlogSettingsReqVO;
import com.kaiming.weblog.module.common.utils.Response;

/**
 * ClassName: AdminBlogSettingsService
 * Package: com.kaiming.weblog.module.admin.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 11:45
 * @Version 1.0
 */
public interface AdminBlogSettingsService {

    /**
     * 更新博客设置信息
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);
}

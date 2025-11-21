package com.kaiming.weblog.module.admin.service;

import com.kaiming.weblog.module.admin.model.vo.UpdateAdminUserPasswordReqVO;
import com.kaiming.weblog.module.common.utils.Response;

/**
 * ClassName: AdminUserService
 * Package: com.kaiming.weblog.module.admin.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/21 12:55
 * @Version 1.0
 */
public interface AdminUserService {

    /**
     * 修改密码
     * @param updateAdminUserPasswordReqVO
     * @return
     */
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);
}

package com.kaiming.weblog.module.admin.controller;

import com.kaiming.weblog.module.admin.model.vo.UpdateAdminUserPasswordReqVO;
import com.kaiming.weblog.module.admin.service.AdminUserService;
import com.kaiming.weblog.module.common.aspect.ApiOperationLog;
import com.kaiming.weblog.module.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: AdminUserController
 * Package: com.kaiming.weblog.module.admin.controller
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/21 12:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin用户模块")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;
    
    @PostMapping("/password/update")
    @ApiOperation(value = "修改用户密码")
    @ApiOperationLog(description = "修改密码")
    public Response updatePassword(@RequestBody @Validated UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        return adminUserService.updatePassword(updateAdminUserPasswordReqVO);
    }

    @PostMapping("/user/info")
    @ApiOperation(value = "获取用户信息")
    @ApiOperationLog(description = "获取用户信息")
    public Response findUserInfo() {
        return adminUserService.findUserInfo();
    }
}

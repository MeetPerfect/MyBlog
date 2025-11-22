package com.kaiming.weblog.module.admin.controller;

import com.kaiming.weblog.module.admin.model.vo.PublishArticleReqVO;
import com.kaiming.weblog.module.admin.service.AdminArticleService;
import com.kaiming.weblog.module.common.aspect.ApiOperationLog;
import com.kaiming.weblog.module.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: AdminArticleController
 * Package: com.kaiming.weblog.module.admin.controller
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 12:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/article")
@Api(tags = "Admin 文章模块")
public class AdminArticleController {

    @Autowired
    private AdminArticleService articleService;

    @PostMapping("/publish")
    @ApiOperation(value = "文章发布")
    @ApiOperationLog(description = "文章发布")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response publishArticle(@RequestBody @Validated PublishArticleReqVO publishArticleReqVO) {
        return articleService.publishArticle(publishArticleReqVO);
    }
}

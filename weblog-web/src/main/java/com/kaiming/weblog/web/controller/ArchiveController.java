package com.kaiming.weblog.web.controller;

import com.kaiming.weblog.module.common.aspect.ApiOperationLog;
import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.model.vo.FindArchiveArticlePageListReqVO;
import com.kaiming.weblog.web.service.ArchiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ArchiveController
 * Package: com.kaiming.weblog.web.controller
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 20:50
 * @Version 1.0
 */
@RestController
@Api(tags = "文章归档")
public class ArchiveController {

    @Autowired
    private ArchiveService archiveService;

    @PostMapping("/archive/list")
    @ApiOperation(value = "获取文章归档分页数据")
    @ApiOperationLog(description = "获取文章归档分页数据")
    public Response findArchivePageList(@RequestBody FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO) {
        return archiveService.findArchivePageList(findArchiveArticlePageListReqVO);
    }
}

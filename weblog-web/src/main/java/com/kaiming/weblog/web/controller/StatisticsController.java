package com.kaiming.weblog.web.controller;

import com.kaiming.weblog.module.common.aspect.ApiOperationLog;
import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: StatisticsController
 * Package: com.kaiming.weblog.web.controller
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 13:27
 * @Version 1.0
 */
@RestController
@RequestMapping("/statistics")
@Api(tags = "统计信息")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @PostMapping("/info")
    @ApiOperation(value = "前台获取统计信息")
    @ApiOperationLog(description = "前台获取统计信息")
    public Response findInfo() {
        return statisticsService.findInfo();
    }
}

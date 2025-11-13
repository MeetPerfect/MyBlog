package com.kaiming.weblog.web.controller;

import com.kaiming.weblog.module.common.aspect.ApiOperationLog;
import com.kaiming.weblog.module.common.utils.JsonUtil;
import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * ClassName: TestController
 * Package: com.kaiming.weblog.web.controller
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/13 17:08
 * @Version 1.0
 */
@RestController
@Api(tags = "首页模块")
@Slf4j
public class TestController {


    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "测试接口")
    public Response test(@RequestBody @Validated User user) {
        // 打印入参
        log.info(JsonUtil.toJsonString(user));

        // 设置三种日期字段值
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());

        return Response.success(user);
    }
}

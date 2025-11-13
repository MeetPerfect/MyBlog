package com.kaiming.weblog.web.controller;

import com.kaiming.weblog.module.common.aspect.ApiOperationLog;
import com.kaiming.weblog.web.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
public class TestController {


    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public User test(@RequestBody User user) {
        // 返参
        return user;
    }
}

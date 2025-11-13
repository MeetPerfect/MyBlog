package com.kaiming.weblog.module.common.aspect;

import java.lang.annotation.*;

/**
 * ClassName: ApiOperationLog
 * Package: com.kaiming.weblog.module.common.aspect
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/13 17:00
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiOperationLog {

    /**
     * API 功能描述
     * @return
     */
    String description() default "";
}

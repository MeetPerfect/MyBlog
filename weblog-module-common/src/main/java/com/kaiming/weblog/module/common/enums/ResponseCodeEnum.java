package com.kaiming.weblog.module.common.enums;

import com.kaiming.weblog.module.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: ResponseCodeEnum
 * Package: com.kaiming.weblog.module.common.enums
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/13 17:25
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {

    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("10000", "出错啦，后台小哥正在努力修复中..."),

    // ----------- 业务异常状态码 -----------
    PRODUCT_NOT_FOUND("20000", "该产品不存在（测试使用）"),
    PARAM_NOT_VALID("10001", "参数错误"),
    ;

    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;
}

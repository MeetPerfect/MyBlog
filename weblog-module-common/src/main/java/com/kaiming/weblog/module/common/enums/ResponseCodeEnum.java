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
    PARAM_NOT_VALID("10001", "出错啦，后台小哥正在努力修复中..."),
    LOGIN_FAIL("10002", "登录失败，请联系管理员"),

    // ----------- 业务异常状态码 -----------,
    PRODUCT_NOT_FOUND("20000", "该产品不存在（测试使用）"),
    USERNAME_OR_PWD_ERROR("20001", "用户名或密码错误"),
    UNAUTHORIZED("20002", "无访问权限，请先登录！"),
    USERNAME_NOT_FOUND("20003", "用户名不存在"),
    FORBIDDEN("20004", "演示账号仅支持查询操作！"),
    CATEGORY_NAME_IS_EXISTED("20005", "该分类已存在, 请勿重复添加！"),
    TAG_CANT_DUPLICATE("20006", "请勿添加已存在的标签"),
    TAG_NOT_EXISTED("20007", "该标签不存在"),
    FILE_UPLOAD_FAILED("20008", "文件上传失败"),
    CATEGORY_NOT_EXISTED("20009", "该分类不存在"),
    ARTICLE_NOT_FOUND("20010", "该文章不存在"),
    ;
    
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;
}

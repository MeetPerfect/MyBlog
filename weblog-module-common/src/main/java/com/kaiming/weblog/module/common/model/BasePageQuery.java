package com.kaiming.weblog.module.common.model;

import lombok.Data;

/**
 * ClassName: BasePageQuery
 * Package: com.kaiming.weblog.module.common.model
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/21 16:21
 * @Version 1.0
 */
@Data
public class BasePageQuery {

    /**
     * 当前页码, 默认第一页
     */
    private Long current = 1L;
    /**
     * 每页展示的数据数量，默认每页展示 10 条数据
     */
    private Long size = 10L;
}

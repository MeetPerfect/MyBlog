package com.kaiming.weblog.module.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: ArticleTypeEnum
 * Package: com.kaiming.weblog.module.common.enums
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/7 17:20
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum ArticleTypeEnum {

    NORMAL(1, "普通"),
    WIKI(2, "收录于知识库");

    private Integer value;
    private String description;
}

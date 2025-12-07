package com.kaiming.weblog.module.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: WikiCatalogLevelEnum
 * Package: com.kaiming.weblog.module.common.enums
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/7 17:20
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum WikiCatalogLevelEnum {

    // 一级目录
    ONE(1),
    // 二级目录
    TWO(2);

    private Integer value;
}

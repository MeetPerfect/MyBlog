package com.kaiming.weblog.module.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: SelectRspVO
 * Package: com.kaiming.weblog.module.common.domain.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/21 17:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectRspVO {
    /**
     * Select 下拉列表的展示文字
     */
    private String label;

    /**
     * Select 下拉列表的 value 值，如 ID 等
     */
    private Object value;
}

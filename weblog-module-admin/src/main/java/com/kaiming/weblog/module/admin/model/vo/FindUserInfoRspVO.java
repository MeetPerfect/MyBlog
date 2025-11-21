package com.kaiming.weblog.module.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ClassName: FindUserInfoRspVO
* Package: com.kaiming.weblog.module.admin.model.vo
* Description:
* @Auther gongkaiming
* @Create 2025/11/21 13:43
* @Version 1.0
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserInfoRspVO {

    /**
     * 用户名
     */
    private String username;
}

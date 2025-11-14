package com.kaiming.weblog.module.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: LoginRspVO
 * Package: com.kaiming.weblog.module.jwt.model
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/14 10:16
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRspVO {

    /**
     * Token 值
     */
    private String token;
}

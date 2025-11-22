package com.kaiming.weblog.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * ClassName: FindTagArticlePagelistRspVO
 * Package: com.kaiming.weblog.web.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 21:58
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagArticlePageListRspVO {

    private Long id;
    private String cover;
    private String title;
    /**
     * 发布日期
     */
    private LocalDate createDate;
}

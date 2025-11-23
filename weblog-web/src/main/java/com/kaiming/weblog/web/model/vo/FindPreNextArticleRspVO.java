package com.kaiming.weblog.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FindPreNextArticleRspVO
 * Package: com.kaiming.weblog.web.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 10:13
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindPreNextArticleRspVO {

    /**
     * 文章 ID
     */
    private Long articleId;

    /**
     * 文章标题
     */
    private String articleTitle;
}

package com.kaiming.weblog.web.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FindArticleDetailReqVO
 * Package: com.kaiming.weblog.web.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 10:12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询文章详情 VO")
public class FindArticleDetailReqVO {

    /**
     * 文章 ID
     */
    private Long articleId;
}

package com.kaiming.weblog.web.model.vo;

import com.kaiming.weblog.module.common.model.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * ClassName: FindCategoryArticlePageListReqVO
 * Package: com.kaiming.weblog.web.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 20:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCategoryArticlePageListReqVO extends BasePageQuery {

    /**
     * 分类 ID
     */
    @NotNull(message = "分类 ID 不能为空")
    private Long id;
}

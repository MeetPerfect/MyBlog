package com.kaiming.weblog.module.admin.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * ClassName: UpdateArticleIsTopReqVO
 * Package: com.kaiming.weblog.module.admin.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/7 14:24
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "更新文章置顶状态 VO")
public class UpdateArticleIsTopReqVO {

    @NotNull(message = "文章 ID 不能为空")
    private Long id;

    @NotNull(message = "文章置顶状态不能为空")
    private Boolean isTop;
}

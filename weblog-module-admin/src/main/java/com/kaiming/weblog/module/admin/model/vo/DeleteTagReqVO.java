package com.kaiming.weblog.module.admin.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * ClassName: DeleteCategoryReqVO
 * Package: com.kaiming.weblog.module.admin.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/21 16:57
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除标签 VO")
public class DeleteTagReqVO {


    @NotNull(message = "标签 ID 不能为空")
    private Long id;
}

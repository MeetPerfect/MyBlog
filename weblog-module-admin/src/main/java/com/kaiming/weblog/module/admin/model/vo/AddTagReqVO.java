package com.kaiming.weblog.module.admin.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * ClassName: AddTagReqVO
 * Package: com.kaiming.weblog.module.admin.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/21 21:30
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "添加标签 VO")
public class AddTagReqVO {
    
    @NotEmpty(message = "标签集合不能为空")
    private List<String> tags;
}

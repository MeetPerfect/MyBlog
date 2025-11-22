package com.kaiming.weblog.web.model.vo;

import com.kaiming.weblog.module.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * ClassName: FindArchiveArticlePageListReqVO
 * Package: com.kaiming.weblog.web.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 20:29
 * @Version 1.0
 */
@Data
@Builder
@ApiModel(value = "文章归档分页 VO")
public class FindArchiveArticlePageListReqVO extends BasePageQuery {
    
    
}

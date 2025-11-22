package com.kaiming.weblog.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.List;

/**
 * ClassName: FindArchiveArticlePageListRspVO
 * Package: com.kaiming.weblog.web.model.vo
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 20:30
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArchiveArticlePageListRspVO {

    /**
     * 归档的月份
     */
    private YearMonth month;

    private List<FindArchiveArticleRspVO> articles;
}

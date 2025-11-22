package com.kaiming.weblog.module.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ArticleContentDO
 * Package: com.kaiming.weblog.module.common.domain.dos
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 12:24
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_article_content")
public class ArticleContentDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long articleId;

    private String content;
}

package com.kaiming.weblog.module.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kaiming.weblog.module.common.domain.dos.ArticleCategoryRelDO;
import com.kaiming.weblog.module.common.domain.dos.ArticleContentDO;

/**
 * ClassName: ArticleMapper
 * Package: com.kaiming.weblog.module.common.domain.mapper
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 12:24
 * @Version 1.0
 */
public interface ArticleCategoryRelMapper extends BaseMapper<ArticleCategoryRelDO> {

    /**
     * 根据文章 ID 删除关联记录
     * @param articleId
     * @return
     */
    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleCategoryRelDO>lambdaQuery()
                .eq(ArticleCategoryRelDO::getArticleId, articleId));
    }

    /**
     * 根据文章 ID 查询关联记录
     * @param articleId
     * @return
     */
    default ArticleCategoryRelDO selectByArticleId(Long articleId) {
        return selectOne(Wrappers.<ArticleCategoryRelDO>lambdaQuery()
                .eq(ArticleCategoryRelDO::getArticleId, articleId));
    }
}

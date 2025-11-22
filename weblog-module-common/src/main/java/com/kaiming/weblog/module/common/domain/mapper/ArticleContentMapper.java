package com.kaiming.weblog.module.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
public interface ArticleContentMapper extends BaseMapper<ArticleContentDO> {

    /**
     * 根据文章ID删除文章内容
     * @param id
     * @return
     */
    default int deleteByArticleId(Long id) {
        return delete(Wrappers.<ArticleContentDO>lambdaQuery()
                .eq(ArticleContentDO::getArticleId, id));
    }

    /**
     * 根据文章ID查询文章内容
     * @param articleId
     * @return
     */
    default ArticleContentDO selectArticleId(Long articleId) {
        return selectOne(Wrappers.<ArticleContentDO>lambdaQuery()
                .eq(ArticleContentDO::getArticleId, articleId));
    }
}

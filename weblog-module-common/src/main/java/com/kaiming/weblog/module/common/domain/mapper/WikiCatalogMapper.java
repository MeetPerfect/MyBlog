package com.kaiming.weblog.module.common.domain.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kaiming.weblog.module.common.config.InsertBatchMapper;
import com.kaiming.weblog.module.common.domain.dos.WikiCatalogDO;

import java.util.List;

/**
 * ClassName: WikiCatalogMapper
 * Package: com.kaiming.weblog.module.common.domain.mapper
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/7 16:46
 * @Version 1.0
 */
public interface WikiCatalogMapper extends InsertBatchMapper<WikiCatalogDO> {

    /**
     * 根据某个知识库下所有目录
     * @param wikiId
     * @return
     */
    default List<WikiCatalogDO> selectByWikiId(Long wikiId) {
        return selectList(Wrappers.<WikiCatalogDO>lambdaQuery()
                .eq(WikiCatalogDO::getWikiId, wikiId)
        );
    }

    /**
     * 删除知识库
     * @param wikiId
     * @return
     */
    default int deleteByWikiId(Long wikiId) {
        return delete(Wrappers.<WikiCatalogDO>lambdaQuery()
                .eq(WikiCatalogDO::getWikiId, wikiId));
    }
}

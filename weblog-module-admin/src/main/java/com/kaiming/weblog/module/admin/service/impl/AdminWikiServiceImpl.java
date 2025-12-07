package com.kaiming.weblog.module.admin.service.impl;

import com.kaiming.weblog.module.admin.model.vo.AddWikiReqVO;
import com.kaiming.weblog.module.admin.model.vo.DeleteWikiReqVO;
import com.kaiming.weblog.module.admin.service.AdminWikiService;
import com.kaiming.weblog.module.common.domain.dos.ArticleDO;
import com.kaiming.weblog.module.common.domain.dos.WikiCatalogDO;
import com.kaiming.weblog.module.common.domain.dos.WikiDO;
import com.kaiming.weblog.module.common.domain.mapper.ArticleMapper;
import com.kaiming.weblog.module.common.domain.mapper.WikiCatalogMapper;
import com.kaiming.weblog.module.common.domain.mapper.WikiMapper;
import com.kaiming.weblog.module.common.enums.ArticleTypeEnum;
import com.kaiming.weblog.module.common.enums.ResponseCodeEnum;
import com.kaiming.weblog.module.common.enums.WikiCatalogLevelEnum;
import com.kaiming.weblog.module.common.exception.BizException;
import com.kaiming.weblog.module.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ClassName: AdminWikiServiceImpl
 * Package: com.kaiming.weblog.module.admin.service.impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/7 16:48
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminWikiServiceImpl implements AdminWikiService {

    @Autowired
    private WikiMapper wikiMapper;
    @Autowired
    private WikiCatalogMapper wikiCatalogMapper;
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 新增知识库
     *
     * @param addWikiReqVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response addWiki(AddWikiReqVO addWikiReqVO) {

        // VO 转 DO
        WikiDO wikiDO = WikiDO.builder()
                .cover(addWikiReqVO.getCover())
                .title(addWikiReqVO.getTitle())
                .summary(addWikiReqVO.getSummary())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        // 新增知识库
        wikiMapper.insert(wikiDO);
        // 获取新增记录的主键 ID
        Long wikiId = wikiDO.getId();

        // 初始化默认目录
        // > 概述
        // > 基础
        wikiCatalogMapper.insert(WikiCatalogDO.builder()
                .wikiId(wikiId)
                .title("概述")
                .sort(1)
                .build());
        wikiCatalogMapper.insert(WikiCatalogDO.builder()
                .wikiId(wikiId)
                .title("基础")
                .sort(2)
                .build());
        return Response.success();
    }

    /**
     * 删除知识库
     *
     * @param deleteWikiReqVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deleteWiki(DeleteWikiReqVO deleteWikiReqVO) {
        Long wikiId = deleteWikiReqVO.getId();
        int count = wikiMapper.deleteById(wikiId);

        if (count == 0) {
            log.warn("该知识库不存在, wikiId: {}", wikiId);
            throw new BizException(ResponseCodeEnum.WIKI_NOT_FOUND);
        }

        List<WikiCatalogDO> wikiCatalogDOS = wikiCatalogMapper.selectByWikiId(wikiId);
        // 过滤目录中所有文章的 ID
        List<Long> articleIds = wikiCatalogDOS.stream()
                .filter(wikiCatalogDO -> Objects.nonNull(wikiCatalogDO.getArticleId())
                        && Objects.equals(wikiCatalogDO.getLevel(), WikiCatalogLevelEnum.TWO.getValue()))
                .map(WikiCatalogDO::getArticleId)
                .collect(Collectors.toList());

        // 更新文章类型 type 为普通
        if (!CollectionUtils.isEmpty(articleIds)) {
            articleMapper.updateByIds(ArticleDO.builder()
                    .type(ArticleTypeEnum.NORMAL.getValue())
                    .build(), 
                    articleIds);
        }
        
        return null;
    }
}

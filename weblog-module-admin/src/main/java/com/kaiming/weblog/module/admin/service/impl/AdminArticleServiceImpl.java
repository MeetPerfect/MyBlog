package com.kaiming.weblog.module.admin.service.impl;

import com.google.common.collect.Lists;
import com.kaiming.weblog.module.admin.model.vo.PublishArticleReqVO;
import com.kaiming.weblog.module.admin.service.AdminArticleService;
import com.kaiming.weblog.module.common.domain.dos.*;
import com.kaiming.weblog.module.common.domain.mapper.*;
import com.kaiming.weblog.module.common.enums.ResponseCodeEnum;
import com.kaiming.weblog.module.common.exception.BizException;
import com.kaiming.weblog.module.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ClassName: AdminArticleService
 * Package: com.kaiming.weblog.module.admin.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 12:28
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminArticleServiceImpl implements AdminArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;

    /**
     * 发布文章
     *
     * @param publishArticleReqVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response publishArticle(PublishArticleReqVO publishArticleReqVO) {
        // 1. VO 转 ArticleDO, 并保存
        ArticleDO articleDO = ArticleDO.builder()
                .title(publishArticleReqVO.getTitle())
                .cover(publishArticleReqVO.getCover())
                .summary(publishArticleReqVO.getSummary())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        articleMapper.insert(articleDO);

        Long articleId = articleDO.getId();

        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .id(articleId)
                .content(publishArticleReqVO.getContent())
                .build();
        articleContentMapper.insert(articleContentDO);

        Long categoryId = publishArticleReqVO.getCategoryId();

        CategoryDO categoryDO = categoryMapper.selectById(categoryId);

        if (Objects.isNull(categoryDO)) {
            log.warn("==> 分类不存在, categoryId: {}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }

        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        
        articleCategoryRelMapper.insert(articleCategoryRelDO);

        List<String> tags = publishArticleReqVO.getTags();

        insertTags(articleId, tags);

        return Response.success();
    }

    /**
     * 插入标签
     * @param articleId
     * @param publishTags
     */
    private void insertTags(Long articleId, List<String> publishTags) {
        List<String> notExistTags = null; 
        List<String> existedTags  = null;

        List<TagDO> tagDOS = tagMapper.selectList(null);

        if (CollectionUtils.isEmpty(tagDOS)) {
            notExistTags = publishTags;
        } else {
            
            List<String> tagIds = tagDOS.stream().map(tagDO -> String.valueOf(tagDO.getId())).collect(Collectors.toList());

            existedTags = publishTags.stream().filter(tagIds::contains).collect(Collectors.toList());
            notExistTags = publishTags.stream().filter(publishTag -> !tagIds.contains(publishTag)).collect(Collectors.toList());

            Map<String, Long> tagNameIdMap = tagDOS.stream().collect(Collectors.toMap(tagDO -> tagDO.getName().toLowerCase(), TagDO::getId));

            Iterator<String> iterator = notExistTags.iterator();
            
            while (iterator.hasNext()) {
                String notExistTag  = iterator.next();
                if (tagNameIdMap.containsKey(notExistTag.toLowerCase())) {
                    iterator.remove();
                    existedTags.add(String.valueOf(tagNameIdMap.get(notExistTag.toLowerCase())));
                }
            }

        }

        // 将提交的上来的，已存在于表中的标签，文章-标签关联关系入库
        if (!CollectionUtils.isEmpty(existedTags)) {
            List<ArticleTagRelDO> articleTagDOS = existedTags.stream().map(tagId -> ArticleTagRelDO.builder()
                    .tagId(Long.valueOf(tagId))
                    .articleId(articleId)
                    .build()).collect(Collectors.toList());

            // 批量插入
            articleTagRelMapper.insertBatchSomeColumn(articleTagDOS);
        }

        // 将提交的上来的，不存在于表中的标签，入库保存
        if (!CollectionUtils.isEmpty(notExistTags)) {
            List<ArticleTagRelDO> articleTagRelDOS = Lists.newArrayList();
            // 需要先将标签入库，拿到对应标签 ID 后，再把文章-标签关联关系入库
            notExistTags.forEach(tagName -> {
                TagDO tagDO = TagDO.builder()
                        .name(tagName)
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build();
                
                tagMapper.insert(tagDO);

                Long tagId = tagDO.getId();

                // 文章-标签关联关系
                ArticleTagRelDO articleTagRelDO = ArticleTagRelDO.builder()
                        .articleId(articleId)
                        .tagId(tagId)
                        .build();
                articleTagRelDOS.add(articleTagRelDO);
            });

            // 批量插入
            articleTagRelMapper.insertBatchSomeColumn(articleTagRelDOS);
        }
    }
}

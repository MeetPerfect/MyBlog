package com.kaiming.weblog.module.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.kaiming.weblog.module.admin.convert.ArticleDetailConvert;
import com.kaiming.weblog.module.admin.event.DeleteArticleEvent;
import com.kaiming.weblog.module.admin.event.PublishArticleEvent;
import com.kaiming.weblog.module.admin.event.UpdateArticleEvent;
import com.kaiming.weblog.module.admin.model.vo.*;
import com.kaiming.weblog.module.admin.service.AdminArticleService;
import com.kaiming.weblog.module.common.domain.dos.*;
import com.kaiming.weblog.module.common.domain.mapper.*;
import com.kaiming.weblog.module.common.enums.ResponseCodeEnum;
import com.kaiming.weblog.module.common.exception.BizException;
import com.kaiming.weblog.module.common.utils.PageResponse;
import com.kaiming.weblog.module.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
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
    @Autowired
    private ApplicationEventPublisher eventPublisher;

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
                .articleId(articleId)
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

        // 发送文章发布事件
        eventPublisher.publishEvent(new PublishArticleEvent(this, articleId));

        return Response.success();
    }

    /**
     * 删除文章
     *
     * @param deleteArticleReqVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO) {
        Long articleId = deleteArticleReqVO.getId();
        // 1. 删除文章
        articleMapper.deleteById(articleId);

        // 2. 删除文章内容
        articleContentMapper.deleteByArticleId(articleId);

        // 3. 删除文章-分类关联记录
        articleCategoryRelMapper.deleteByArticleId(articleId);

        // 4. 删除文章-标签关联记录
        articleTagRelMapper.deleteByArticleId(articleId);

        // 发布文章删除事件
        eventPublisher.publishEvent(new DeleteArticleEvent(this, articleId));
        
        return Response.success();
    }

    /**
     * 查询文章分页数据
     *
     * @param findArticlePageListReqVO
     * @return
     */
    @Override
    public Response findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO) {
        Long current = findArticlePageListReqVO.getCurrent();

        Long size = findArticlePageListReqVO.getSize();
        String title = findArticlePageListReqVO.getTitle();
        LocalDate startDate = findArticlePageListReqVO.getStartDate();
        LocalDate endDate = findArticlePageListReqVO.getEndDate();

        Page<ArticleDO> articleDOPage = articleMapper.selectPageList(current, size, title, startDate, endDate);

        List<ArticleDO> articleDOS = articleDOPage.getRecords();

        List<FindArticlePageListRspVO> rspVOS = null;
        //转vo
        if (!CollectionUtils.isEmpty(articleDOS)) {
            rspVOS = articleDOS.stream().map(articleDO -> FindArticlePageListRspVO.builder()
                            .id(articleDO.getId())
                            .title(articleDO.getTitle())
                            .cover(articleDO.getCover())
                            .createTime(articleDO.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }
        return PageResponse.success(articleDOPage, rspVOS);
    }

    /**
     * 查询文章详情
     *
     * @param findArticleDetailReqVO
     * @return
     */
    @Override
    public Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO) {
        Long articleId = findArticleDetailReqVO.getId();
        // 文章对象
        ArticleDO articleDO = articleMapper.selectById(articleId);

        if (Objects.isNull(articleDO)) {
            log.warn("==> 查询的文章不存在，articleId: {}", articleId);
            throw new BizException(ResponseCodeEnum.ARTICLE_NOT_FOUND);
        }
        // 文章内容
        ArticleContentDO articleContentDO = articleContentMapper.selectArticleId(articleId);

        // 所属分类
        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectByArticleId(articleId);

        // 对应标签
        List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByArticleId(articleId);
        // 获取对应标签 ID 集合
        List<Long> tagIds = articleTagRelDOS.stream().map(ArticleTagRelDO::getTagId).collect(Collectors.toList());

        FindArticleDetailRspVO vo = ArticleDetailConvert.INSTANCE.convertDO2VO(articleDO);

        vo.setContent(articleContentDO.getContent());
        vo.setCategoryId(articleCategoryRelDO.getCategoryId());
        vo.setTagIds(tagIds);

        return Response.success(vo);
    }

    /**
     * 更新文章
     *
     * @param updateArticleReqVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateArticle(UpdateArticleReqVO updateArticleReqVO) {
        Long articleId = updateArticleReqVO.getId();

        // 1. VO 转 ArticleDO, 并更新
        ArticleDO articleDO = ArticleDO.builder()
                .id(articleId)
                .title(updateArticleReqVO.getTitle())
                .cover(updateArticleReqVO.getCover())
                .summary(updateArticleReqVO.getSummary())
                .updateTime(LocalDateTime.now())
                .build();
        
        int count = articleMapper.updateById(articleDO);

        // 根据更新是否成功，来判断该文章是否存在
        if (count == 0) {
            log.warn("==> 该文章不存在, articleId: {}", articleId);
            throw new BizException(ResponseCodeEnum.ARTICLE_NOT_FOUND);
        }
        // 2. VO 转 ArticleContentDO，并更新
        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .articleId(articleId)
                .content(updateArticleReqVO.getContent())
                .build();

        articleContentMapper.updateByArticleId(articleContentDO);

        // 3. 更新文章分类
        Long categoryId = updateArticleReqVO.getCategoryId();
        // 3.1 校验提交的分类是否真实存在
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 分类不存在, categoryId: {}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }

        // 先删除该文章关联的分类记录，再插入新的关联关系
        articleCategoryRelMapper.deleteByArticleId(articleId);
        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);

        // 4. 保存文章关联的标签集合
        // 先删除该文章对应的标签
        articleTagRelMapper.deleteByArticleId(articleId);
        List<String> publishTags = updateArticleReqVO.getTags();

        insertTags(articleId, publishTags);

        // 发布文章修改事件
        eventPublisher.publishEvent(new UpdateArticleEvent(this, articleId));

        return Response.success();
    }

    /**
     * 插入标签
     *
     * @param articleId
     * @param publishTags
     */
    private void insertTags(Long articleId, List<String> publishTags) {
        List<String> notExistTags = null;
        List<String> existedTags = null;

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
                String notExistTag = iterator.next();
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

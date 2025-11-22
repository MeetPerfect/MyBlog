package com.kaiming.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.kaiming.weblog.module.common.domain.dos.*;
import com.kaiming.weblog.module.common.domain.mapper.*;
import com.kaiming.weblog.module.common.utils.PageResponse;
import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.convert.ArticleConvert;
import com.kaiming.weblog.web.model.vo.FindCategoryListRspVO;
import com.kaiming.weblog.web.model.vo.FindIndexArticlePageListReqVO;
import com.kaiming.weblog.web.model.vo.FindIndexArticlePageListRspVO;
import com.kaiming.weblog.web.model.vo.FindTagListRspVO;
import com.kaiming.weblog.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ClassName: ArticleServiceImpl
 * Package: com.kaiming.weblog.web.service.impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 16:00
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO) {
        Long current = findIndexArticlePageListReqVO.getCurrent();
        Long size = findIndexArticlePageListReqVO.getSize();

        Page<ArticleDO> articleDOPage = articleMapper.selectPageList(current, size, null, null, null);
        // 返回的分页数据
        List<ArticleDO> articleDOS = articleDOPage.getRecords();

        List<FindIndexArticlePageListRspVO> vos = null;

        if (!CollectionUtils.isEmpty(articleDOS)) {
            vos = articleDOS.stream()
                    .map(ArticleConvert.INSTANCE::convertDO2VO)
                    .collect(Collectors.toList());

            List<Long> articleIds = articleDOS.stream().map(ArticleDO::getId).collect(Collectors.toList());

            // 第二步：设置文章所属分类
            // 查询所有分类
            List<CategoryDO> categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());
            Map<Long, String> categoryIdNameMap = categoryDOS.stream()
                    .collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));

            // 根据文章 ID 批量查询所有关联记录
            List<ArticleCategoryRelDO> articleCategoryRelDOS = articleCategoryRelMapper.selectByArticleIds(articleIds);

            vos.forEach(vo -> {
                Long currentArticleId = vo.getId();

                Optional<ArticleCategoryRelDO> optional = articleCategoryRelDOS.stream()
                        .filter(rel -> Objects.equals(rel.getArticleId(), currentArticleId))
                        .findAny();

                if (optional.isPresent()) {
                    ArticleCategoryRelDO articleCategoryRelDO = optional.get();
                    Long categoryId = articleCategoryRelDO.getCategoryId();

                    // 通过分类 ID 从 map 中拿到对应的分类名称
                    String categoryName = categoryIdNameMap.get(categoryId);

                    FindCategoryListRspVO findCategoryListRspVO = FindCategoryListRspVO.builder()
                            .id(categoryId)
                            .name(categoryName)
                            .build();
                    // 设置到当前 vo 类中
                    vo.setCategory(findCategoryListRspVO);
                }
            });

            // 第三步：设置文章标签
            // 查询所有标签
            List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());

            Map<Long, String> mapIdNameMap = tagDOS.stream()
                    .collect(Collectors.toMap(TagDO::getId, TagDO::getName));
            // 拿到所有文章的标签关联记录
            List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByArticleIds(articleIds);

            vos.forEach(vo -> {
                List<ArticleTagRelDO> articleTagRelDOList = articleTagRelDOS.stream()
                        .filter(rel -> Objects.equals(rel.getArticleId(), vo.getId()))
                        .collect(Collectors.toList());

                List<FindTagListRspVO> findTagListRspVOS = Lists.newArrayList();

                // 将关联记录 DO 转 VO, 并设置对应的标签名称
                articleTagRelDOList.forEach(articleTagRelDO -> {
                    Long tagId = articleTagRelDO.getTagId();
                    String tagName = mapIdNameMap.get(tagId);

                    FindTagListRspVO findTagListRspVO = FindTagListRspVO.builder()
                            .id(tagId)
                            .name(tagName)
                            .build();
                    findTagListRspVOS.add(findTagListRspVO);
                });
                
            });
        }


        return PageResponse.success(articleDOPage, vos);
    }
}

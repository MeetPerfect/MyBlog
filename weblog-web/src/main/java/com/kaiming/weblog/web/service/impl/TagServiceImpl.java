package com.kaiming.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaiming.weblog.module.common.domain.dos.ArticleDO;
import com.kaiming.weblog.module.common.domain.dos.ArticleTagRelDO;
import com.kaiming.weblog.module.common.domain.dos.TagDO;
import com.kaiming.weblog.module.common.domain.mapper.ArticleMapper;
import com.kaiming.weblog.module.common.domain.mapper.ArticleTagRelMapper;
import com.kaiming.weblog.module.common.domain.mapper.TagMapper;
import com.kaiming.weblog.module.common.enums.ResponseCodeEnum;
import com.kaiming.weblog.module.common.exception.BizException;
import com.kaiming.weblog.module.common.utils.PageResponse;
import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.convert.ArticleConvert;
import com.kaiming.weblog.web.model.vo.FindTagArticlePageListReqVO;
import com.kaiming.weblog.web.model.vo.FindTagArticlePageListRspVO;
import com.kaiming.weblog.web.model.vo.FindTagListRspVO;
import com.kaiming.weblog.web.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ClassName: TagServiceImpl
 * Package: com.kaiming.weblog.web.service.impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 17:16
 * @Version 1.0
 */
@Service
@Slf4j
public class TagServiceImpl implements TagService {
    
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Response findTagList() {

        List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());

        // DO 转 VO
        List<FindTagListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> FindTagListRspVO.builder()
                            .id(tagDO.getId())
                            .name(tagDO.getName())
                            .build())
                    .collect(Collectors.toList());
        }
        
        return Response.success(vos);
    }

    /**
     * 获取标签下文章分页列表
     * @param findTagArticlePageListReqVO
     * @return
     */
    @Override
    public Response findTagPageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        Long current = findTagArticlePageListReqVO.getCurrent();
        Long size = findTagArticlePageListReqVO.getSize();

        Long tagId = findTagArticlePageListReqVO.getId();

        TagDO tagDO = tagMapper.selectById(tagId);

        if (Objects.isNull(tagDO)) {
            log.warn("==> 该标签不存在, tagId: {}", tagId);
            throw new BizException(ResponseCodeEnum.TAG_NOT_EXISTED);
        }

        List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByTagId(tagId);

        if (CollectionUtils.isEmpty(articleTagRelDOS)) {
            log.info("==> 该标签下还未发布任何文章, tagId: {}", tagId);
            return PageResponse.success(null, null);
        }

        List<Long> articleIds  = articleTagRelDOS.stream()
                .map(ArticleTagRelDO::getArticleId).collect(Collectors.toList());

        Page<ArticleDO> articleDOPage = articleMapper.selectPageListByArticleId(current, size, articleIds);

        List<ArticleDO> articleDOS = articleDOPage.getRecords();

        // DO 转 VO
        List<FindTagArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)) {
            vos = articleDOS.stream()
                    .map(ArticleConvert.INSTANCE::convertDO2TagArticleVO)
                    .collect(Collectors.toList());
        }

        return null;
    }
}

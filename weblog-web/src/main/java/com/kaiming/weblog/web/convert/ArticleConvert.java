package com.kaiming.weblog.web.convert;

import com.kaiming.weblog.module.common.domain.dos.ArticleDO;
import com.kaiming.weblog.web.model.vo.FindArchiveArticleRspVO;
import com.kaiming.weblog.web.model.vo.FindCategoryArticlePageListRspVO;
import com.kaiming.weblog.web.model.vo.FindIndexArticlePageListRspVO;
import com.kaiming.weblog.web.model.vo.FindTagArticlePageListRspVO;
import org.checkerframework.checker.units.qual.A;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: ArticleConvert
 * Package: com.kaiming.weblog.web.convert
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 16:06
 * @Version 1.0
 */
@Mapper
public interface ArticleConvert {

    /**
     * 实例
     */
    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    /**
     * DO 转 VO
     * @param bean
     * @return
     */
    FindIndexArticlePageListRspVO convertDO2VO(ArticleDO bean);
    
    /**
     * DO 转 归档文章 VO
     * @param bean
     * @return
     */
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(bean.getCreateTime()))")
    @Mapping(target = "createMonth", expression = "java(java.time.YearMonth.from(bean.getCreateTime()))")
    FindArchiveArticleRspVO convertDO2ArchiveArticleVO(ArticleDO bean);


    /**
     * 将 DO 转换成分类文章 VO
     * @param bean
     * @return
     */
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(bean.getCreateTime()))")
    FindCategoryArticlePageListRspVO convertDO2CategoryArticleVO(ArticleDO bean);


    /**
     * 将 DO 转换成标签文章 VO
     * @param bean
     * @return
     */
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(bean.getCreateTime()))")
    FindTagArticlePageListRspVO convertDO2TagArticleVO(ArticleDO bean);
}

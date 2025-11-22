package com.kaiming.weblog.module.admin.convert;

import com.kaiming.weblog.module.admin.model.vo.FindArticleDetailRspVO;
import com.kaiming.weblog.module.common.domain.dos.ArticleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: ArticleDetailConvert
 * Package: com.kaiming.weblog.module.admin.convert
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 15:12
 * @Version 1.0
 */
@Mapper
public interface ArticleDetailConvert {

    /**
     * 初始化 convert 实例
     */
    ArticleDetailConvert INSTANCE = Mappers.getMapper(ArticleDetailConvert.class);

    /**
     * 将 DO 转化为 VO
     * @param bean
     * @return
     */
    FindArticleDetailRspVO convertDO2VO(ArticleDO bean);
}

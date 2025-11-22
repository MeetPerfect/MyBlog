package com.kaiming.weblog.web.convert;

import com.kaiming.weblog.module.common.domain.dos.ArticleDO;
import com.kaiming.weblog.web.model.vo.FindIndexArticlePageListRspVO;
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
}

package com.kaiming.weblog.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaiming.weblog.module.common.domain.dos.ArticleDO;
import com.kaiming.weblog.module.common.domain.mapper.ArticleMapper;
import com.kaiming.weblog.module.common.utils.PageResponse;
import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.convert.ArticleConvert;
import com.kaiming.weblog.web.model.vo.FindArchiveArticlePageListReqVO;
import com.kaiming.weblog.web.model.vo.FindArchiveArticlePageListRspVO;
import com.kaiming.weblog.web.model.vo.FindArchiveArticleRspVO;
import com.kaiming.weblog.web.model.vo.FindIndexArticlePageListRspVO;
import com.kaiming.weblog.web.service.ArchiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * ClassName: ArchiveServiceImpl
 * Package: com.kaiming.weblog.web.service.impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 20:33
 * @Version 1.0
 */
@Service
@Slf4j
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private ArticleMapper articleMapper;
    
    @Override
    public Response findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO) {
        Long current = findArchiveArticlePageListReqVO.getCurrent();
        Long size = findArchiveArticlePageListReqVO.getSize();

        Page<ArticleDO> articleDOPage = articleMapper.selectPageList(current, size, null, null, null);

        List<ArticleDO> articleDOS = articleDOPage.getRecords();
        
        List<FindArchiveArticlePageListRspVO> vos = null;

        if (!CollectionUtils.isEmpty(articleDOS)) {
            // DO 转 VO
            List<FindArchiveArticleRspVO> archiveArticleRspVOS  = articleDOS.stream()
                    .map(ArticleConvert.INSTANCE::convertDO2ArchiveArticleVO)
                    .collect(Collectors.toList());
            
            // 按创建的月份进行分组
            Map<YearMonth, List<FindArchiveArticleRspVO>> map = archiveArticleRspVOS.stream()
                    .collect(Collectors.groupingBy(FindArchiveArticleRspVO::getCreateMonth));

            // 使用 TreeMap 按月份倒序排列
            Map<YearMonth, List<FindArchiveArticleRspVO>> sortedMap = new TreeMap<>(Collections.reverseOrder());
            
            sortedMap.putAll(map);
            // 遍历排序后的 Map，将其转换为归档 VO
            sortedMap.forEach((k, v) -> vos.add(FindArchiveArticlePageListRspVO.builder().month(k).articles(v).build()));

        }
        
        return PageResponse.success(articleDOPage, vos);
    }
}

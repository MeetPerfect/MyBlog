package com.kaiming.weblog.module.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kaiming.weblog.module.admin.model.vo.FindDashboardPVStatisticsInfoRspVO;
import com.kaiming.weblog.module.admin.model.vo.FindDashboardStatisticsInfoRspVO;
import com.kaiming.weblog.module.admin.service.AdminDashboardService;
import com.kaiming.weblog.module.common.constant.Constants;
import com.kaiming.weblog.module.common.domain.dos.ArticleDO;
import com.kaiming.weblog.module.common.domain.dos.ArticlePublishCountDO;
import com.kaiming.weblog.module.common.domain.dos.StatisticsArticlePVDO;
import com.kaiming.weblog.module.common.domain.mapper.ArticleMapper;
import com.kaiming.weblog.module.common.domain.mapper.CategoryMapper;
import com.kaiming.weblog.module.common.domain.mapper.StatisticsArticlePVMapper;
import com.kaiming.weblog.module.common.domain.mapper.TagMapper;
import com.kaiming.weblog.module.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ClassName: AdminDashboardServiceImpl
 * Package: com.kaiming.weblog.module.admin.service.impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 10:59
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminDashboardServiceImpl implements AdminDashboardService {
    
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private StatisticsArticlePVMapper statisticsArticlePVMapper;
    
    
    @Override
    public Response findDashboardStatistics() {

        // 查询文章总数
        Long articleTotalCount = articleMapper.selectCount(Wrappers.emptyWrapper());

        // 查询分类总数
        Long categoryTotalCount = categoryMapper.selectCount(Wrappers.emptyWrapper());

        // 查询标签总数
        Long tagTotalCount = tagMapper.selectCount(Wrappers.emptyWrapper());

        // 总浏览量
        List<ArticleDO> articleDOS = articleMapper.selectAllReadNum();

        Long pvTotalCount = 0L;

        if (!CollectionUtils.isEmpty(articleDOS)) {
            // 所有 read_num 相加
            pvTotalCount = articleDOS.stream().mapToLong(ArticleDO::getReadNum).sum();
        }

        // 组装 VO 类
        FindDashboardStatisticsInfoRspVO vo = FindDashboardStatisticsInfoRspVO.builder()
                .articleTotalCount(articleTotalCount)
                .categoryTotalCount(categoryTotalCount)
                .tagTotalCount(tagTotalCount)
                .pvTotalCount(pvTotalCount)
                .build();
        
        return Response.success(vo);
    }

    
    @Override
    public Response findDashboardPublishArticleStatistics() {
        LocalDate current = LocalDate.now();
        LocalDate startDate = current.minusYears(1);

        List<ArticlePublishCountDO> articlePublishCountDOS = articleMapper.selectDateArticlePublishCount(startDate, current);

        Map<LocalDate, Long> map = null;

        if (!CollectionUtils.isEmpty(articlePublishCountDOS)) {
            Map<LocalDate, Long> dateArticleCountMap = articlePublishCountDOS.stream()
                    .collect(Collectors.toMap(ArticlePublishCountDO::getDate, ArticlePublishCountDO::getCount));

            map = Maps.newHashMap();

            // 从上一年的今天循环到今天
            for (; startDate.isBefore(current) || startDate.isEqual(current); startDate = startDate.plusDays(1)) {
                // 以日期作为 key 从 dateArticleCountMap 中取文章发布总量
                Long count = dateArticleCountMap.get(startDate);
                // 设置到返参 Map
                map.put(startDate, Objects.isNull(count) ? 0 : count);
            }
        }
        
        return Response.success(map);
    }

    @Override
    public Response findDashboardPVStatistics() {

        List<StatisticsArticlePVDO> statisticsArticlePVDOS = statisticsArticlePVMapper.selectLatestWeekRecords();

        Map<LocalDate, Long> pvDateCountMap = Maps.newHashMap();

        if (!CollectionUtils.isEmpty(statisticsArticlePVDOS)) {
            // 转 Map, 方便后续通过日期获取 PV 访问量
            pvDateCountMap = statisticsArticlePVDOS.stream()
                    .collect(Collectors.toMap(StatisticsArticlePVDO::getPvDate, StatisticsArticlePVDO::getPvCount));
        }
        FindDashboardPVStatisticsInfoRspVO vo = null;

        // 日期集合
        List<String> pvDates = Lists.newArrayList();
        // PV 集合
        List<Long> pvCounts = Lists.newArrayList();

        LocalDate curr = LocalDate.now();
        LocalDate startDate = curr.minusWeeks(1);

        // 从一周前开始循环
        for (; startDate.isBefore(curr) || startDate.isEqual(curr); startDate = startDate.plusDays(1)) {
            // 设置对应日期的 PV 访问量
            pvDates.add(startDate.format(Constants.MONTH_DAY_FORMATTER));
            Long pvCount = pvDateCountMap.get(startDate);
            pvCounts.add(Objects.isNull(pvCount) ? 0 : pvCount);
        }

        return Response.success(vo);
    }
}

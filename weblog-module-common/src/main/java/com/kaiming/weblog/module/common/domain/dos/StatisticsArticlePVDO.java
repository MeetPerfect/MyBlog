package com.kaiming.weblog.module.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ClassName: StatisticsArticlePVDO
 * Package: com.kaiming.weblog.module.common.domain.dos
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 11:15
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_statistics_article_pv")
public class StatisticsArticlePVDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private LocalDate pvDate;

    private Long pvCount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

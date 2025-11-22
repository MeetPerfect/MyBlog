package com.kaiming.weblog.module.common.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: InsertBatchMapper
 * Package: com.kaiming.weblog.module.common.config
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 12:42
 * @Version 1.0
 */
public interface InsertBatchMapper<T> extends BaseMapper<T> {

    // 批量插入
    int insertBatchSomeColumn(@Param("list") List<T> batchList);
}

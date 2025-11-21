package com.kaiming.weblog.module.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaiming.weblog.module.common.domain.dos.CategoryDO;
import com.kaiming.weblog.module.common.domain.dos.TagDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: CategoryMapper
 * Package: com.kaiming.weblog.module.common.domain.mapper
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/21 15:21
 * @Version 1.0
 */
@Mapper
public interface TagMapper extends BaseMapper<TagDO> {
    
    
}

package com.kaiming.weblog.module.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaiming.weblog.module.common.domain.dos.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.Objects;

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
public interface CategoryMapper extends BaseMapper<CategoryDO> {

    /**
     * 分页查询分类列表
     * @param current
     * @param size
     * @param name
     * @param startDate
     * @param endDate
     * @return
     */
    default Page<CategoryDO> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate) {
        
        Page<CategoryDO> page = new Page<>();
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.isNotBlank(name), CategoryDO::getName, name.trim())
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)
                .orderByDesc(CategoryDO::getCreateTime);
        
        return selectPage(page, wrapper);
    }
    
    /**
     * 根据分类名称查询
     * @param categoryName
     * @return
     */
    default CategoryDO selectByName(String categoryName) {
        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryDO::getName, categoryName);

        // 执行查询
        return selectOne(wrapper);
    }
    
}

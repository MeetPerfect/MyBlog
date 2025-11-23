package com.kaiming.weblog.module.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaiming.weblog.module.common.domain.dos.TagDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
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
public interface TagMapper extends BaseMapper<TagDO> {

    /**
     * 根据标签名称查询
     *
     * @param current
     * @param size
     * @param name
     * @param startTime
     * @param endTime
     * @return
     */
    default Page<TagDO> selectPageList(Long current, Long size, String name, LocalDate startTime, LocalDate endTime) {
        Page<TagDO> page = new Page<>(current, size);

        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(name), TagDO::getName, name)
                .ge(Objects.nonNull(startTime), TagDO::getCreateTime, startTime)
                .le(Objects.nonNull(endTime), TagDO::getCreateTime, endTime)
                .orderByDesc(TagDO::getCreateTime);

        return selectPage(page, wrapper);
    }

    /**
     * 根据关键字模糊查询标签列表
     *
     * @param key
     * @return
     */
    default List<TagDO> selectByKey(String key) {

        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(TagDO::getName, key).orderByDesc(TagDO::getCreateTime);

        return selectList(wrapper);
    }

    /**
     * 根据标签ID列表查询标签列表
     *
     * @param tagIds
     * @return
     */
    default List<TagDO> selectByIds(List<Long> tagIds) {
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(TagDO::getId, tagIds);
        return selectList(wrapper);
    }
}

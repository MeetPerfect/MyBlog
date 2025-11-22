package com.kaiming.weblog.module.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaiming.weblog.module.admin.model.vo.*;
import com.kaiming.weblog.module.admin.service.AdminTagService;
import com.kaiming.weblog.module.common.domain.dos.TagDO;
import com.kaiming.weblog.module.common.domain.mapper.TagMapper;
import com.kaiming.weblog.module.common.domain.vo.SelectRspVO;
import com.kaiming.weblog.module.common.enums.ResponseCodeEnum;
import com.kaiming.weblog.module.common.utils.PageResponse;
import com.kaiming.weblog.module.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ClassName: AdminCategoryServiceImpl
 * Package: com.kaiming.weblog.module.admin.service.impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/21 15:26
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements AdminTagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Response addTag(AddTagReqVO addTagReqVO) {
        List<TagDO> tagDOS = addTagReqVO.getTags().stream()
                .map(TagName -> TagDO.builder()
                        .name(TagName.trim())
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());
        // 批量插入
        try {
            saveBatch(tagDOS);
        } catch (Exception e) {
            log.warn("该标签已存在, e");
        }
        return Response.success();
    }

    @Override
    public PageResponse findTagList(FindTagPageListReqVO findTagPageListReqVO) {
        Long current = findTagPageListReqVO.getCurrent();
        Long size = findTagPageListReqVO.getSize();

        String name = findTagPageListReqVO.getName();
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();

        // 分页查询
        Page<TagDO> page = tagMapper.selectPageList(current, size, name, startDate, endDate);

        List<TagDO> records = page.getRecords();

        List<FindTagPageListRspVO> vos = null;

//        转vo
        if (!CollectionUtils.isEmpty(records)) {
            vos = records.stream().map(tagDO -> FindTagPageListRspVO.builder()
                            .id(tagDO.getId())
                            .name(tagDO.getName())
                            .createTime(tagDO.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }
        return PageResponse.success(page, vos);
    }

    /**
     * 删除分类
     *
     * @param deleteTagReqVO
     * @return
     */
    @Override
    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
        Long id = deleteTagReqVO.getId();
        int count = tagMapper.deleteById(id);
        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.TAG_NOT_EXISTED);
    }

    /**
     * 标签 Select 下拉列表数据获取
     *
     * @param searchTagReqVO
     * @return
     */
    @Override
    public Response searchTag(SearchTagReqVO searchTagReqVO) {
        String key = searchTagReqVO.getKey();

        List<TagDO> tagDOS = tagMapper.selectByKey(key);

        List<SelectRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(Objects.toString(tagDO.getId()))
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(vos);
    }

    /**
     * 查询标签 Select 列表数据
     *
     * @return
     */
    @Override
    public Response findTagSelectList() {
        List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());

        List<SelectRspVO> vos = null;

        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(Objects.toString(tagDO.getId()))
                            .build()).collect(Collectors.toList());
        }
        return Response.success(vos);
    }
}

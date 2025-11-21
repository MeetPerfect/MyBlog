package com.kaiming.weblog.module.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaiming.weblog.module.admin.model.vo.*;
import com.kaiming.weblog.module.admin.service.AdminCategoryService;
import com.kaiming.weblog.module.admin.service.AdminTagService;
import com.kaiming.weblog.module.common.domain.dos.CategoryDO;
import com.kaiming.weblog.module.common.domain.dos.TagDO;
import com.kaiming.weblog.module.common.domain.mapper.CategoryMapper;
import com.kaiming.weblog.module.common.domain.mapper.TagMapper;
import com.kaiming.weblog.module.common.domain.vo.SelectRspVO;
import com.kaiming.weblog.module.common.enums.ResponseCodeEnum;
import com.kaiming.weblog.module.common.exception.BizException;
import com.kaiming.weblog.module.common.utils.PageResponse;
import com.kaiming.weblog.module.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

//    @Override
//    public PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
//    }
//
//    @Override
//    public Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO) {
//    }
//
//    @Override
//    public Response findCategorySelectList() {
//        
//    }
}

package com.kaiming.weblog.module.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaiming.weblog.module.admin.model.vo.AddCategoryReqVO;
import com.kaiming.weblog.module.admin.model.vo.DeleteCategoryReqVO;
import com.kaiming.weblog.module.admin.model.vo.FindCategoryPageListReqVO;
import com.kaiming.weblog.module.admin.model.vo.FindCategoryPageListRspVO;
import com.kaiming.weblog.module.admin.service.AdminCategoryService;
import com.kaiming.weblog.module.common.domain.dos.ArticleCategoryRelDO;
import com.kaiming.weblog.module.common.domain.dos.CategoryDO;
import com.kaiming.weblog.module.common.domain.mapper.ArticleCategoryRelMapper;
import com.kaiming.weblog.module.common.domain.mapper.CategoryMapper;
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
public class AdminCategoryServiceImpl implements AdminCategoryService {
    
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    
    @Override
    public Response addCategory(AddCategoryReqVO addCategoryReqVO) {
        String categoryName  = addCategoryReqVO.getName();

        CategoryDO categoryDO = categoryMapper.selectByName(categoryName);

        if (Objects.nonNull(categoryDO)) {
            log.warn("分类名称： {}, 此分类已存在", categoryName);
            throw new BizException(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }

        CategoryDO insertCategoryDO = CategoryDO.builder()
                .name(categoryName.trim())
                .build();
        
        categoryMapper.insert(insertCategoryDO);

        return Response.success();
    }

    @Override
    public PageResponse findCategoryPageList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
        // 获取当前页、以及每页需要展示的数据数量
        Long current = findCategoryPageListReqVO.getCurrent();
        Long size = findCategoryPageListReqVO.getSize();
        
        String name = findCategoryPageListReqVO.getName();
        LocalDate startDate = findCategoryPageListReqVO.getStartDate();
        LocalDate endDate = findCategoryPageListReqVO.getEndDate();

        Page<CategoryDO> categoryDOPage = categoryMapper.selectPageList(current, size, name, startDate, endDate);
        
        List<CategoryDO> categoryDOS = categoryDOPage.getRecords();

        // DO 转 VO
        List<FindCategoryPageListRspVO> vos = null;

        if (!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream()
                    .map(categoryDO -> {
                        return FindCategoryPageListRspVO.builder()
                                .id(categoryDO.getId())
                                .name(categoryDO.getName())
                                .createTime(categoryDO.getCreateTime())
                                .build();
                    }).collect(Collectors.toList());
        }
        
        return PageResponse.success(categoryDOPage, vos);
    }

    @Override
    public Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO) {
        // 分类 ID
        Long categoryId = deleteCategoryReqVO.getId();
        
        // 判断分类下是否存在文章
        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectOneByCategoryId(categoryId);

        if (Objects.nonNull(articleCategoryRelDO)) {
            log.warn("==> 此分类下包含文章，无法删除，categoryId: {}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_CAN_NOT_DELETE);
        }
        
        // 删除分类
        categoryMapper.deleteById(categoryId);

        return Response.success();
    }

    @Override
    public Response findCategorySelectList() {
        List<CategoryDO> categoryDOS = categoryMapper.selectList(null);

        // DO 转 VO
        List<SelectRspVO> selectRspVOS = null;

        if (!CollectionUtils.isEmpty(categoryDOS)) {
            selectRspVOS = categoryDOS.stream()
                    .map(categoryDO -> SelectRspVO.builder()
                            .label(categoryDO.getName())
                            .value(categoryDO.getId())
                            .build()).collect(Collectors.toList());
        }
        
        return Response.success(selectRspVOS);
    }
}

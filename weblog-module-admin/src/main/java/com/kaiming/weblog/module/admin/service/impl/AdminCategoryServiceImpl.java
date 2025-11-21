package com.kaiming.weblog.module.admin.service.impl;

import com.kaiming.weblog.module.admin.model.vo.AddCategoryReqVO;
import com.kaiming.weblog.module.admin.service.AdminCategoryService;
import com.kaiming.weblog.module.common.domain.dos.CategoryDO;
import com.kaiming.weblog.module.common.domain.mapper.CategoryMapper;
import com.kaiming.weblog.module.common.enums.ResponseCodeEnum;
import com.kaiming.weblog.module.common.exception.BizException;
import com.kaiming.weblog.module.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
}

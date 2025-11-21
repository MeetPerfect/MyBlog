package com.kaiming.weblog.module.admin.service;

import com.kaiming.weblog.module.admin.model.vo.AddCategoryReqVO;
import com.kaiming.weblog.module.admin.model.vo.DeleteCategoryReqVO;
import com.kaiming.weblog.module.admin.model.vo.FindCategoryPageListReqVO;
import com.kaiming.weblog.module.common.utils.PageResponse;
import com.kaiming.weblog.module.common.utils.Response;

/**
 * ClassName: AdminCategoryService
 * Package: com.kaiming.weblog.module.admin.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/21 15:26
 * @Version 1.0
 */
public interface AdminCategoryService {

    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);

    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    PageResponse findCategoryPageList(FindCategoryPageListReqVO findCategoryPageListReqVO);


    /**
     * 删除分类
     * @param deleteCategoryReqVO
     * @return
     */
    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);


    /**
     * 获取文章分类的 Select 列表数据
     * @return
     */
    Response findCategorySelectList();
}

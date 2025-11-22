package com.kaiming.weblog.module.admin.service;

import com.kaiming.weblog.module.admin.model.vo.*;
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
public interface AdminTagService {

    /**
     * 添加分类
     *
     * @param addTagReqVO
     * @return
     */
    Response addTag(AddTagReqVO addTagReqVO);

    /**
     * 分类分页数据查询
     *
     * @param findTagPageListReqVO
     * @return
     */
    PageResponse findTagList(FindTagPageListReqVO findTagPageListReqVO);


    /**
     * 删除分类
     *
     * @param deleteTagReqVO
     * @return
     */
    Response deleteTag(DeleteTagReqVO deleteTagReqVO);


    /**
     * 获取文章分类的 Select 列表数据
     *
     * @return
     */
    Response searchTag(SearchTagReqVO searchTagReqVO);

    
    /**
     * 查询标签 Select 列表数据
     *
     * @return
     */
    Response findTagSelectList();
}

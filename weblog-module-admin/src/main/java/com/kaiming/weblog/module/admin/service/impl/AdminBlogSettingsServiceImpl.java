package com.kaiming.weblog.module.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaiming.weblog.module.admin.convert.BlogSettingsConvert;
import com.kaiming.weblog.module.admin.model.vo.UpdateBlogSettingsReqVO;
import com.kaiming.weblog.module.admin.service.AdminBlogSettingsService;
import com.kaiming.weblog.module.common.domain.dos.BlogSettingsDO;
import com.kaiming.weblog.module.common.domain.mapper.BlogSettingsMapper;
import com.kaiming.weblog.module.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: AdminBlogSettingsServiceImpl
 * Package: com.kaiming.weblog.module.admin.service.impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 11:45
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingsService {

    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        // VO 转 DO
//        BlogSettingsDO blogSettingsDO = BlogSettingsDO.builder()
//                .id(1L)
//                .logo(updateBlogSettingsReqVO.getLogo())
//                .name(updateBlogSettingsReqVO.getName())
//                .author(updateBlogSettingsReqVO.getAuthor())
//                .introduction(updateBlogSettingsReqVO.getIntroduction())
//                .avatar(updateBlogSettingsReqVO.getAvatar())
//                .githubHomepage(updateBlogSettingsReqVO.getGithubHomepage())
//                .giteeHomepage(updateBlogSettingsReqVO.getGiteeHomepage())
//                .csdnHomepage(updateBlogSettingsReqVO.getCsdnHomepage())
//                .zhihuHomepage(updateBlogSettingsReqVO.getZhihuHomepage())
//                .build();
        BlogSettingsDO blogSettingsDO = BlogSettingsConvert.INSTANCE.convertVO2DO(updateBlogSettingsReqVO);
        blogSettingsDO.setId(1L);
        
        // 保存或更新（当数据库中存在 ID 为 1 的记录时，则执行更新操作，否则执行插入操作）
        saveOrUpdate(blogSettingsDO);
        return Response.success();
        
    }
    
}

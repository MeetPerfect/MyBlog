package com.kaiming.weblog.web.service.impl;

import com.kaiming.weblog.module.common.domain.dos.BlogSettingsDO;
import com.kaiming.weblog.module.common.domain.mapper.BlogSettingsMapper;
import com.kaiming.weblog.module.common.utils.Response;
import com.kaiming.weblog.web.convert.BlogSettingsConvert;
import com.kaiming.weblog.web.model.vo.FindBlogSettingsDetailRspVO;
import com.kaiming.weblog.web.service.BlogSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: BlogSettingServiceImpl
 * Package: com.kaiming.weblog.web.service.impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 17:22
 * @Version 1.0
 */
@Service
@Slf4j
public class BlogSettingServiceImpl implements BlogSettingsService {
    
    @Autowired
    private BlogSettingsMapper blogSettingsMapper;

    /**
     * 获取博客设置信息
     * @return
     */
    @Override
    public Response findDetail() {

        // 查询博客设置信息（约定的 ID 为 1）
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        // DO 转 VO
        FindBlogSettingsDetailRspVO vo = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);
        
        return Response.success(vo);
    }
}

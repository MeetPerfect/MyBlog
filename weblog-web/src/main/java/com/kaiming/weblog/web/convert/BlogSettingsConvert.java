package com.kaiming.weblog.web.convert;

import com.kaiming.weblog.module.common.domain.dos.BlogSettingsDO;
import com.kaiming.weblog.web.model.vo.FindBlogSettingsDetailRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: BlogSettingsConvert
 * Package: com.kaiming.weblog.web.convert
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 17:20
 * @Version 1.0
 */
@Mapper
public interface BlogSettingsConvert {
    
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    FindBlogSettingsDetailRspVO convertDO2VO(BlogSettingsDO bean);
}

package com.kaiming.weblog.module.admin.convert;

import com.kaiming.weblog.module.admin.model.vo.UpdateBlogSettingsReqVO;
import com.kaiming.weblog.module.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: BlogSettingConvert
 * Package: com.kaiming.weblog.module.admin.convert
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 11:59
 * @Version 1.0
 */
@Mapper
public interface BlogSettingsConvert {

    /**
     * 初始化 convert 实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * 将 VO 转化为 DO
     * @param bean
     * @return
     */
    BlogSettingsDO convertVO2DO(UpdateBlogSettingsReqVO bean);
}

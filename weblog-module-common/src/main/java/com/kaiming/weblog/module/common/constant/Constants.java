package com.kaiming.weblog.module.common.constant;

import java.time.format.DateTimeFormatter;

/**
 * ClassName: Constants
 * Package: com.kaiming.weblog.module.common.constant
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 11:28
 * @Version 1.0
 */
public interface Constants {

    /**
     * 月-日 格式
     */
    DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");

    /**
     * 年-月-日 小时-分钟-秒
     */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}

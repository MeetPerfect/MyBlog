package com.kaiming.weblog.module.admin.service;

import com.kaiming.weblog.module.common.utils.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: AdminFileService
 * Package: com.kaiming.weblog.module.admin.service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 11:37
 * @Version 1.0
 */
public interface AdminFileService {

    /**
     * 上传文件
     * @param file
     * @return
     */
    Response uploadFile(MultipartFile file);
}

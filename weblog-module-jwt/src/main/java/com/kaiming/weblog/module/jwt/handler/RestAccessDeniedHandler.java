package com.kaiming.weblog.module.jwt.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: RestAccessDeniedHandler
 * Package: com.kaiming.weblog.module.jwt.handler
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/14 10:32
 * @Version 1.0
 */
@Component
@Slf4j
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("登录成功访问收保护的资源，但是权限不够: ", accessDeniedException);
        // 预留，后面引入多角色时会用到
    }
}

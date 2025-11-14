package com.kaiming.weblog.module.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * ClassName: UsernameOrPasswordNullException
 * Package: com.kaiming.weblog.module.jwt.exception
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/14 10:13
 * @Version 1.0
 */
public class UsernameOrPasswordNullException extends AuthenticationException {

    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}

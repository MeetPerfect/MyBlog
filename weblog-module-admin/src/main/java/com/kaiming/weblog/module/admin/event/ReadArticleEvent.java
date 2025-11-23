package com.kaiming.weblog.module.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * ClassName: ReadArticleEvent
 * Package: com.kaiming.weblog.module.admin.event
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 10:49
 * @Version 1.0
 */
@Getter
public class ReadArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;
    
    public ReadArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}

package com.kaiming.weblog.module.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * ClassName: PublishArticleEvent
 * Package: com.kaiming.weblog.module.admin.event
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 19:33
 * @Version 1.0
 */
@Getter
public class PublishArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;

    public PublishArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}

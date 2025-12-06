package com.kaiming.weblog.module.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * ClassName: UpdateArticleEvent
 * Package: com.kaiming.weblog.module.admin.event
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 19:42
 * @Version 1.0
 */
@Getter
public class UpdateArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;

    public UpdateArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}

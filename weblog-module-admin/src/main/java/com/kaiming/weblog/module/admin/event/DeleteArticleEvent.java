package com.kaiming.weblog.module.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * ClassName: DeleteArticleEvent
 * Package: com.kaiming.weblog.module.admin.event
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 19:40
 * @Version 1.0
 */
@Getter
public class DeleteArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;

    public DeleteArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}

package com.kaiming.weblog.module.admin.event.subscriber;

import com.kaiming.weblog.module.admin.event.ReadArticleEvent;
import com.kaiming.weblog.module.common.domain.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: ReadArticleSubscriber
 * Package: com.kaiming.weblog.module.admin.event.subscriber
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/23 10:50
 * @Version 1.0
 */
@Component
@Slf4j
public class ReadArticleSubscriber implements ApplicationListener<ReadArticleEvent> {

    @Autowired
    private ArticleMapper articleMapper;
    
    @Override
    public void onApplicationEvent(ReadArticleEvent event) {
        Long articleId = event.getArticleId();

        // 获取当前线程名称
        String threadName = Thread.currentThread().getName();

        log.info("==> threadName: {}", threadName);
        log.info("==> 文章阅读事件消费成功，articleId: {}", articleId);

        articleMapper.increaseReadNum(articleId);
    }
}

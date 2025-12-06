package com.kaiming.weblog.search.runner;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.kaiming.weblog.module.common.constant.Constants;
import com.kaiming.weblog.module.common.domain.dos.ArticleContentDO;
import com.kaiming.weblog.module.common.domain.dos.ArticleDO;
import com.kaiming.weblog.module.common.domain.mapper.ArticleContentMapper;
import com.kaiming.weblog.module.common.domain.mapper.ArticleMapper;
import com.kaiming.weblog.search.LuceneHelper;
import com.kaiming.weblog.search.config.LuceneProperties;
import com.kaiming.weblog.search.index.ArticleIndex;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * ClassName: InitLuceneIndexRunner
 * Package: com.kaiming.weblog.search.runner
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 13:39
 * @Version 1.0
 */
@Component
@Slf4j
public class InitLuceneIndexRunner implements CommandLineRunner {
    
    @Autowired
    private LuceneProperties luceneProperties;
    @Autowired
    private LuceneHelper luceneHelper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("==> 开始初始化 Lucene 索引...");

        // 所有文章列表
        List<ArticleDO> articleDOS = articleMapper.selectList(Wrappers.emptyWrapper());

        if (articleDOS.isEmpty()) {
            log.info("==> 未发布任何文章，暂不创建 Lucene 索引...");
            return;
        }
        if (StringUtils.isBlank(luceneProperties.getIndexDir())) {
            log.error("==> 未指定 Lucene 索引存放位置，需在 application.yml 文件中添加路径配置...");
            return;
        }

        // 文章索引存放目录， 如 /app/weblog/lucene-index/article
        String articleIndexDir = luceneProperties.getIndexDir() + File.separator + ArticleIndex.NAME;

        List<Document> documents = Lists.newArrayList();
        articleDOS.forEach(articleDO -> {
            Long articleId = articleDO.getId();

            // 查询文章正文
            ArticleContentDO articleContentDO = articleContentMapper.selectArticleId(articleId);
            // 构建文档
            Document document = new Document();
            // 设置文档字段 Field
            document.add(new TextField(ArticleIndex.COLUMN_ID, String.valueOf(articleId), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_TITLE, articleDO.getTitle(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_COVER, articleDO.getCover(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_SUMMARY, articleDO.getSummary(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_CONTENT, articleContentDO.getContent(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_CREATE_TIME, Constants.DATE_TIME_FORMATTER.format(articleDO.getCreateTime()), Field.Store.YES));
            documents.add(document);
        });

        // 创建索引
        luceneHelper.createIndex(ArticleIndex.NAME, documents);

        log.info("==> 结束初始化 Lucene 索引...");
        
    }
}

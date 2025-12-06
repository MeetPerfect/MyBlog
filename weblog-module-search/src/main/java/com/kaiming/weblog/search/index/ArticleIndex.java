package com.kaiming.weblog.search.index;

/**
 * ClassName: ArticleIndex
 * Package: com.kaiming.weblog.search.index
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/12/6 14:09
 * @Version 1.0
 */

public interface ArticleIndex {
    /**
     * 索引名称
     */
    String NAME = "article";

    // --------------------- 文档字段 ---------------------
    String COLUMN_ID = "id";

    String COLUMN_TITLE = "title";

    String COLUMN_COVER = "cover";

    String COLUMN_SUMMARY = "summary";

    String COLUMN_CONTENT = "content";

    String COLUMN_CREATE_TIME = "createTime";
}

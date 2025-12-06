package com.kaiming.weblog.web.markdown;

import com.kaiming.weblog.web.markdown.provider.NofollowLinkAttributeProvider;
import com.kaiming.weblog.web.markdown.renderer.ImageNodeRenderer;
import com.kaiming.weblog.web.markdown.renderer.LinkNodeRenderer;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.ext.image.attributes.ImageAttributesExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: MarkDownHelper
 * Package: com.kaiming.weblog.web.markdown
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 22:28
 * @Version 1.0
 */
public class MarkDownHelper {
    
    private final static Parser PARSER;

    /**
     * HTML 渲染器
     */
    private final static HtmlRenderer HTML_RENDERER;

    /**
     * 初始化
     */
    static {
        // Markdown 拓展
        List<Extension> extensions = Arrays.asList(
                TablesExtension.create(), // 表格拓展
                HeadingAnchorExtension.create(), // 标题锚定项
                ImageAttributesExtension.create(), // 图片宽高
                TaskListItemsExtension.create() // 任务列表
        );
        
        PARSER = Parser.builder().extensions(extensions).build();
        HTML_RENDERER = HtmlRenderer.builder()
                .extensions(extensions)
                .nodeRendererFactory(ImageNodeRenderer::new)
                .nodeRendererFactory(LinkNodeRenderer::new)
                .attributeProviderFactory(context -> new NofollowLinkAttributeProvider())
                .nodeRendererFactory(ImageNodeRenderer::new)
                .build();
    }

    /**
     * 将 Markdown 转换成 HTML
     * @param markdown
     * @return
     */
    public static String convertMarkdown2Html(String markdown) {
        Node document = PARSER.parse(markdown);
        return HTML_RENDERER.render(document);
    }

    public static void main(String[] args) {
        String markdown = "This is *Sparta*";
        System.out.println(MarkDownHelper.convertMarkdown2Html(markdown));
    }
}

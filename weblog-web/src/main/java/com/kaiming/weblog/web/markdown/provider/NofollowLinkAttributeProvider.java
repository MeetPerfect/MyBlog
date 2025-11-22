package com.kaiming.weblog.web.markdown.provider;

import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

/**
 * ClassName: NofollowLinkAttributeProvider
 * Package: com.kaiming.weblog.web.markdown
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2025/11/22 22:27
 * @Version 1.0
 */

public class NofollowLinkAttributeProvider implements AttributeProvider {

    /**
     * 网站域名（上线后需要改成自己的域名）
     */
    private final static String DOMAIN = "www.meetperfect.com";
    
    @Override
    public void setAttributes(Node node, String s, Map<String, String> attributes) {
        if (node instanceof Link) {
            Link linkNode = (Link) node;
            // 获取链接地址
            String href = linkNode.getDestination();
            // 如果链接不是自己域名，则添加 rel="nofollow" 属性
            if (!href.contains(DOMAIN)) {
                attributes.put("rel", "nofollow");
            }
        }
    }
}

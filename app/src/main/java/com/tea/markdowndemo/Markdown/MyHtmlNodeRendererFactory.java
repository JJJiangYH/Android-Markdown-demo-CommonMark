package com.tea.markdowndemo.Markdown;

import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;

/**
 * 节点渲染工厂
 *
 * @author jiang yuhang
 * @version 1.0
 * @className my
 * @program My Application
 * @date 2021-04-26 10:46
 */
public class MyHtmlNodeRendererFactory implements HtmlNodeRendererFactory {
    @Override
    public NodeRenderer create(HtmlNodeRendererContext htmlNodeRendererContext) {
        return new MyNodeRenderer(htmlNodeRendererContext);
    }
}
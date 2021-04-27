package com.tea.markdowndemo.Markdown;

import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

import java.util.Collections;
import java.util.Set;

/**
 * 自定义节点渲染
 *
 * @author jiang yuhang
 * @version 1.0
 * @className myno
 * @program My Application
 * @date 2021-04-26 10:47
 */
public class MyNodeRenderer implements NodeRenderer {
    private final HtmlWriter html;

    MyNodeRenderer(HtmlNodeRendererContext context) {
        this.html = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        //返回我们要使用此渲染器的节点类型
        return Collections.singleton(IndentedCodeBlock.class);
    }

    @Override
    public void render(Node node) {
        IndentedCodeBlock codeBlock = (IndentedCodeBlock) node;
        html.line();
        html.tag("pre");
        html.text(codeBlock.getLiteral());
        html.tag("/pre");
        html.line();
    }
}
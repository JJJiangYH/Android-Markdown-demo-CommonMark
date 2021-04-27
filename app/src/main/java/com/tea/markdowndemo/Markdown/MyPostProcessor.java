package com.tea.markdowndemo.Markdown;

import org.commonmark.node.Node;
import org.commonmark.parser.PostProcessor;

/**
 * 解析后处理
 *
 * @author jiang yuhang
 * @version 1.0
 * @className myp
 * @program My Application
 * @date 2021-04-26 10:50
 */
public class MyPostProcessor implements PostProcessor {
    @Override
    public Node process(Node node) {
        return node;
    }
}
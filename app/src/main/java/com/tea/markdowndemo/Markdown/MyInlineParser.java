package com.tea.markdowndemo.Markdown;

import org.commonmark.node.Node;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.InlineParserContext;
import org.commonmark.parser.SourceLines;

/**
 * markdown字符串解析
 *
 * @author jiang yuhang
 * @version 1.0
 * @className mys
 * @program My Application
 * @date 2021-04-26 10:48
 */
public class MyInlineParser implements InlineParser {
    private final InlineParserContext inlineParserContext;

    public MyInlineParser(InlineParserContext inlineParserContext) {
        this.inlineParserContext = inlineParserContext;
    }

    /**
     * @param lines the source content to parse as inline
     * @param node  the node to append resulting nodes to (as children)
     */
    @Override
    public void parse(SourceLines lines, Node node) {

    }
}
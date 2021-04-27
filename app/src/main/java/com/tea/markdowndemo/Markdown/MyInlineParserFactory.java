package com.tea.markdowndemo.Markdown;

import org.commonmark.parser.InlineParser;
import org.commonmark.parser.InlineParserContext;
import org.commonmark.parser.InlineParserFactory;

/**
 * 解析工厂
 *
 * @author jiang yuhang
 * @version 1.0
 * @className my
 * @program My Application
 * @date 2021-04-26 10:48
 */
public class MyInlineParserFactory implements InlineParserFactory {
    @Override
    public InlineParser create(InlineParserContext inlineParserContext) {
        return new MyInlineParser(inlineParserContext);
    }
}
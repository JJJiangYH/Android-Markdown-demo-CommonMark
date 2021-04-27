package com.tea.markdowndemo.Markdown;

import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;

/**
 * 自定义属性提供工厂
 *
 * @author jiang yuhang
 * @version 1.0
 * @className s
 * @program My Application
 * @date 2021-04-26 10:44
 */
public class MyAttributeProviderFactory implements AttributeProviderFactory {
    @Override
    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
        return new MyAttributeProvider();
    }
}
package com.tea.markdowndemo.Markdown;

import org.commonmark.renderer.html.UrlSanitizer;

/**
 * url处理器
 *
 * @author jiang yuhang
 * @version 1.0
 * @className my
 * @program My Application
 * @date 2021-04-26 10:49
 */
public class MyUrlSanitizer implements UrlSanitizer {
    @Override
    public String sanitizeLinkUrl(String s) {
        return s + "?demo=123456";
    }

    @Override
    public String sanitizeImageUrl(String s) {
        return s + "?demo=666666";
    }
}
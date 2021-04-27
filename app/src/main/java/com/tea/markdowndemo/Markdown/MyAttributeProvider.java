package com.tea.markdowndemo.Markdown;

import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

/**
 * 自定义属性提供类
 *
 * @author jiang yuhang
 * @version 1.0
 * @className My
 * @program My Application
 * @date 2021-04-26 10:45
 */
public class MyAttributeProvider implements AttributeProvider {
    @Override
    public void setAttributes(Node node, String s, Map<String, String> map) {
        if (node instanceof Image) {
            map.put("style", "width:150px;height:200px;position:relative;left:50%;margin-left:-100px;");
        }
        if (node instanceof Link) {
            map.put("target", "_blank");
        }
    }
}
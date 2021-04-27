package com.tea.markdowndemo.Markdown;

import android.util.Log;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.node.Document;
import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.node.Paragraph;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author jiang yuhang
 * @version 1.0
 * @className Markdown
 * @program teaHome
 * @date 2021-04-26 11:08
 */
public class Markdown {
    final static List<Extension> extensions = Arrays.asList(
            TablesExtension.create(),   //表格解析
//            AutolinkExtension.create(),  //url格式的文本转成链接
            StrikethroughExtension.create(), //删除线
            HeadingAnchorExtension.create(),    //航向锚
            InsExtension.create(),  //下划线
            TaskListItemsExtension.create() //列表
    );
    final static HashSet enabledBlockTypes = new HashSet(Arrays.asList(
            Document.class, Image.class, Link.class, Paragraph.class, Text.class
    ));

    static Parser parser = Parser.builder()
//                .enabledBlockTypes(enabledBlockTypes)//添加启用块,会报错,暂时不知怎么用
            .extensions(extensions)
            .postProcessor(new MyPostProcessor())//对解析后的 Node 对象进行处理
//            .inlineParserFactory(new MyInlineParserFactory())//用于自定义 markdown字符串解析
//                .customBlockParserFactory(new MyBlockParserFactory())
//                .customDelimiterProcessor(new MyDelimiterProcessor())
            .build();
    static HtmlRenderer renderer = HtmlRenderer.builder()
            .extensions(extensions)//设置扩展
//            .sanitizeUrls(true)//是否url处理
//            .urlSanitizer(new MyUrlSanitizer())//设置自定义url处理器
//            .attributeProviderFactory(new MyAttributeProviderFactory())//设置元素提供工厂
            .nodeRendererFactory(new MyHtmlNodeRendererFactory())//设置节点渲染工厂
            .escapeHtml(false)//html转码
            .softbreak("<br/>")
            .percentEncodeUrls(false)//url编码处理
            .build();

    public static String parse(String s) {
        Log.e("render", "<body bgcolor=\"#faebd7\">" + renderer.render(parser.parse(s)) + "</body>");
        return "<body bgcolor=\"#f1e8d9\">\n" +     //设置网页背景颜色
                renderer.render(parser.parse(s)) +
                "</body>\n";
    }
}
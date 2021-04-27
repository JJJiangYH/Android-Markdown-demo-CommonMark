package com.tea.markdowndemo.Activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tea.markdowndemo.Dialog.DialogFragment;
import com.tea.markdowndemo.R;
import com.tea.markdowndemo.Toast.Toast;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tea.markdowndemo.Markdown.Markdown.parse;

/**
 * @author jiang yuhang
 * @version 1.0
 * @className MarkdownUIActivity
 * @program teaHome
 * @date 2021-04-26 21:05
 */
public class MarkdownUIActivity extends AppCompatActivity
        implements View.OnClickListener {

    public static final String PAGE = "page";
    public static final String DEF_VALUE = "";
    public static final String TITLE = "title";
    public static final String TIME = "time";

    private final static String mimeType = "text/html";
    private final static String enCoding = "utf-8";
    private final static String MARKDOWN = "MARKDOWN";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_title)
    EditText et_title;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.markdown)
    EditText markdown;

    @SuppressLint("DefaultLocale")
    protected final Handler handler = new Handler(msg -> {
        String title = et_title.getText().toString();
        String page = markdown.getText().toString();

        Date date = new Date();

        if (msg.what == DialogFragment.MARKDOWN_SAVE) {
            //保存草稿
            SharedPreferences preferences = getSharedPreferences(MARKDOWN, MODE_PRIVATE);
            preferences.edit()
                    .putLong(TIME, date.getTime())
                    .putString(PAGE, page)
                    .putString(TITLE, title)
                    .apply();
        } else if (msg.what == DialogFragment.MARKDOWN_DROP) {
            //清除草稿
            SharedPreferences preferences = getSharedPreferences(MARKDOWN, MODE_PRIVATE);
            preferences.edit()
                    .putLong(TIME, 0)
                    .putString(PAGE, DEF_VALUE)
                    .putString(TITLE, DEF_VALUE)
                    .apply();
        } else if (msg.what == DialogFragment.MARKDOWN_SHOW) {
            //读取草稿
            SharedPreferences preferences = getSharedPreferences(MARKDOWN, MODE_PRIVATE);

            page = preferences.getString(PAGE, DEF_VALUE);
            title = preferences.getString(TITLE, DEF_VALUE);

            if (page.length() == 0 && title.length() == 0) {
                return true;
            } else {
                long time = ((new Date()).getTime() - preferences.getLong(TIME, 0)) / 1000;
                String tip = "已经恢复为%d%s前的草稿。";

                if (time < 60) {
                    tip = String.format(tip, time, "秒");
                } else {
                    tip = String.format(tip, time / 60, "分钟");
                }

                markdown.setText(page);
                et_title.setText(title);
                Toast.getToast(this, tip).show();
            }
        }

        if (msg.obj != null && msg.obj.equals("Dialog")) {
            super.finish();
        }
        return true;
    });

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.markdown_webView)
    WebView markdown_webView;
    @BindViews({R.id.title, R.id.bold, R.id.italics, R.id.delete, R.id.inline, R.id.list})
    List<Button> buttons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markdown);
        ButterKnife.bind(this);

        this.findViewById(R.id.markdown_webView)
                .setBackgroundColor(getResources().getColor(R.color.app_bg_color));
    }

    /**
     * @param isEdit 当前状态
     */
    protected void changeEditMode(View v, boolean isEdit) {
        if (v instanceof Button) {
            Button button = (Button) v;
            markdown_webView.setVisibility(isEdit ? View.VISIBLE : View.GONE);
            markdown.setVisibility(isEdit ? View.GONE : View.VISIBLE);
            button.setTag(isEdit ? "edit" : "see");
            button.setText(isEdit ? "编辑" : "预览");

            for (Button b : buttons) {
                b.setEnabled(!isEdit);
                if (isEdit) {
                    b.setText(DEF_VALUE);
                } else {
                    b.setText(b.getHint());
                }
            }
            if (isEdit) {
                String text = markdown.getText().toString();
                markdown_webView.loadDataWithBaseURL(null,
                        parse(text),
                        mimeType,
                        enCoding,
                        null);
            }
        }
    }

    /**
     * 上传文章
     */
    protected void updatePage() {
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @OnClick({R.id.see, R.id.title, R.id.bold, R.id.italics, R.id.delete,
            R.id.inline, R.id.list})
    public void onClick(View v) {

    }
}

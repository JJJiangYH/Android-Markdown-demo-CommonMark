package com.tea.markdowndemo.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;

import com.tea.markdowndemo.Dialog.DialogFragment;

/**
 * @author jiang yuhang
 * @version 1.0
 * @className MarkdownActivity
 * @program teaHome
 * @date 2021-04-24 18:27
 */
public class MarkdownActivity extends MarkdownUIActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        switch (tag) {
            case "see":
                changeEditMode(v, true);
                break;
            case "edit":
                changeEditMode(v, false);
                break;
            case "update":
                updatePage();
                break;
            default:
                int start = markdown.getSelectionStart();
                int end = markdown.getSelectionEnd();
                if (start == end) {
                    markdown.getText().insert(start, tag);
                } else {
                    markdown.getText().replace(start, end, tag);
                }
                if (tag.length() != 2 || tag.equals("__")) {
                    markdown.setSelection(markdown.getSelectionStart() - tag.length() / 2);
                }
                if (markdown.requestFocus()) {
                    InputMethodManager imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(markdown, InputMethodManager.SHOW_IMPLICIT);
                }
                break;
        }
    }

    /**
     * 检查草稿箱是否有文章，如果存在，读取并显示
     * 否则置为空
     *
     * @author jiang yuhang
     * @date 2021-04-27 19:47
     **/
    @Override
    protected void onResume() {
        handler.sendEmptyMessage(DialogFragment.MARKDOWN_SHOW);
        super.onResume();
    }

    /**
     * 询问用户是否保存未完成的文章
     *
     * @author jiang yuhang
     * @date 2021-04-27 19:48
     **/
    @Override
    public void finish() {
        int textCount = markdown.getText().length() + et_title.getText().length();

        if (textCount != 0) {
            DialogFragment dialogFragment =
                    new DialogFragment(DialogFragment.MARKDOWN_MODE, handler);
            dialogFragment.show(getSupportFragmentManager(), "DialogFragment");
        } else {
            handler.sendEmptyMessage(DialogFragment.MARKDOWN_DROP);
            super.finish();
        }
    }
}
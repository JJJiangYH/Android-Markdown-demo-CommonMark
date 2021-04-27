package com.tea.markdowndemo.Toast;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tea.markdowndemo.R;

/**
 * 显示一个自定义的Toast
 *
 * @author : jiang yuhang
 * @version 1.0
 * @date : 2021-02-22 11:11
 */
public class Toast {
    /**
     * 显示一个自定义的Toast
     *
     * @param activity 根活动
     * @param s        要显示的文字
     * @return android.widget.Toast 自定义的toast
     * @author jiang yuhang
     * @date 2021-02-17 22:52
     **/
    public static android.widget.Toast getToast(Activity activity, String s) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast
                , activity.findViewById(R.id.toast_layout_root));
        if (s != null) {
            TextView text = layout.findViewById(R.id.text);
            text.setText(s);
        }

        //初始化Toast
        android.widget.Toast toast = new android.widget.Toast(activity);
        toast.setGravity(Gravity.BOTTOM, 0, 300);
        toast.setView(layout);
        return toast;
    }

    /**
     * 显示一个自定义的Toast
     *
     * @param context 上下文
     * @param s       要显示的文字
     * @return android.widget.Toast 自定义的toast
     * @author jiang yuhang
     * @date 2021-02-26 14:25
     **/
    public static android.widget.Toast getToast(Context context, String s) {
        Activity activity = (Activity) context;
        return getToast(activity, s);
    }
}
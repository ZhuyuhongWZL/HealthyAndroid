package com.example.a67371.tabtest.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.ui.calendar.MaterialCalendarView;
import com.example.a67371.tabtest.ui.calendar.OnDateSelectedListener;

/**
 * Created by wenjie on 2018/3/15.
 */

public class DateDialog extends Dialog {
    Activity context;

    MaterialCalendarView calendar;

    private OnDateSelectedListener mClickListener;

    public DateDialog(Activity context, int theme, OnDateSelectedListener clickListener) {
        super(context, theme);
        this.context = context;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.date_dialog);

        Window dialogWindow = this.getWindow();
        dialogWindow.setGravity(Gravity.TOP);
        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = 1320;
        p.width = 1080;
        dialogWindow.setAttributes(p);
        dialogWindow.setWindowAnimations(R.style.calendar_anim);
        calendar = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangedListener(mClickListener);
        this.setCancelable(true);
    }

}

package com.example.a67371.tabtest.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.a67371.tabtest.R;

/**
 * Created by wenjie on 2018/3/15.
 */

public class EvaluateDialog extends Dialog {
    Activity context;

    private Button button;

    private RatingBar evaluate;

    private View.OnClickListener mClickListener;

    public EvaluateDialog(Activity context, int theme, View.OnClickListener clickListener) {
        super(context, theme);
        this.context = context;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.evaluate_dialog);

        evaluate = (RatingBar) findViewById(R.id.evaluate);

        // 根据id在布局中找到控件对象
        button = (Button) findViewById(R.id.evaluate_button);

        // 为按钮绑定点击事件监听器
        button.setOnClickListener(mClickListener);

        Window dialogWindow = this.getWindow();

        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = 420;
        p.width = 750;
        dialogWindow.setAttributes(p);
        this.setCancelable(true);
    }

    public RatingBar getEvaluate() {
        return evaluate;
    }
}

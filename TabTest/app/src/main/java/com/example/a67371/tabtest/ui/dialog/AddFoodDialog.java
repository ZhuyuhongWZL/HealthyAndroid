package com.example.a67371.tabtest.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.a67371.tabtest.R;

/**
 * Created by wenjie on 2018/3/13.
 */

public class AddFoodDialog extends Dialog {
    Activity context;

    private Button button;

    private EditText foodNumber;

    private View.OnClickListener mClickListener;

    private int i;

    public AddFoodDialog(Activity context) {
        super(context);
        this.context = context;
    }

    public AddFoodDialog(Activity context, int theme, View.OnClickListener clickListener, int i) {
        super(context, theme);
        this.context = context;
        this.mClickListener = clickListener;
        this.i = i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.add_food_dialog);

        foodNumber = (EditText) findViewById(R.id.add_food_number);

        // 根据id在布局中找到控件对象
        button = (Button) findViewById(R.id.add_food_number_button);

        // 为按钮绑定点击事件监听器
        button.setOnClickListener(mClickListener);

        Window dialogWindow = this.getWindow();

        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = 465; // 高度设置为屏幕的0.6
        p.width = 600; // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);

        this.setCancelable(true);
    }

    public int getI() {
        return i;
    }

    public EditText getFoodNumber() {
        return foodNumber;
    }
}

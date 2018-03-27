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

public class AddNewFoodDialog extends Dialog {
    Activity context;

    private Button button;

    private EditText foodName;

    private EditText foodAlias;

    private EditText foodKarori;

    private View.OnClickListener mClickListener;


    public AddNewFoodDialog(Activity context) {
        super(context);
        this.context = context;
    }

    public AddNewFoodDialog(Activity context, int theme, View.OnClickListener clickListener) {
        super(context, theme);
        this.context = context;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.add_new_food_dialog);

        foodName = (EditText) findViewById(R.id.add_new_food_name);
        foodAlias = (EditText) findViewById(R.id.add_new_food_alias);
        foodKarori = (EditText) findViewById(R.id.add_new_food_karori);

        // 根据id在布局中找到控件对象
        button = (Button) findViewById(R.id.add_new_food_button);

        // 为按钮绑定点击事件监听器
        button.setOnClickListener(mClickListener);

        Window dialogWindow = this.getWindow();

        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = 885; // 高度设置为屏幕的0.6
        p.width = 600; // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);

        this.setCancelable(true);
    }

    public EditText getFoodName() {
        return foodName;
    }

    public EditText getFoodAlias() {
        return foodAlias;
    }

    public EditText getFoodKarori() {
        return foodKarori;
    }
}

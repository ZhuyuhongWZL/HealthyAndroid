package com.example.a67371.tabtest.ui.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wenjie on 2018/3/13.
 */

public class DietView extends View implements Runnable {
    private Paint mPaint;
    private TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
    private Context mContext;
    private int screenWidth = 1080;
    private int screenHeight = 900;
    private int radial = 280;
    private int time = 0;

    private int totalKarori = 1800;
    private int restKarori = 1800;
    private float arc = 0;

    private RectF oval;

    public DietView(Context context) {
        super(context, null);
        init();
        postInvalidate();
    }

    public DietView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
        postInvalidate();
    }

    /**
     * 由于onDraw()方法会不停的绘制 所以需要定义一个初始化画笔方法 避免导致不停创建造成内存消耗过大
     */
    private void init() {
        mPaint = new Paint();
        // 设置画笔为抗锯齿
        mPaint.setAntiAlias(true);
        // 设置颜色为红色
        mPaint.setColor(Color.WHITE);
        /**
         * 画笔样式分三种： 1.Paint.Style.STROKE：描边 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        mPaint.setStyle(Paint.Style.STROKE);
        /**
         * 设置描边的粗细，单位：像素px 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
         */
        mPaint.setStrokeWidth(10);
        oval = new RectF( screenWidth / 2 - radial, screenHeight / 2 - radial -72, screenWidth / 2 + radial, screenHeight / 2 + radial - 72);
        textPaint.setTextSize(50);// 字体大小
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);// 采用默认的宽度
        textPaint.setColor(Color.argb(100,255,255,255));// 采用的颜色
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 绘制圆环drawCircle的前两个参数表示圆心的XY坐标， 这里我们用到了一个工具类获取屏幕尺寸以便将其圆心设置在屏幕中心位置，
         * 第三个参数是圆的半径，第四个参数则为我们的画笔
         *
         */
        textPaint.setTextSize(50);
        mPaint.setAlpha(100);
        String s;
        s = "" + totalKarori;
        canvas.drawCircle(screenWidth / 2, screenHeight / 2 - 72, radial, mPaint);
        canvas.drawText("还可以吃", screenWidth / 2 - 100, screenHeight / 2 - 192, textPaint);
        canvas.drawText("预算 " + s, screenWidth / 2 - 62.5f - 12.5f * s.length(), screenHeight / 2 + 98, textPaint);
        canvas.drawText("单位：千卡", screenWidth / 2 - 125, screenHeight / 2 + 308, textPaint);
        s = "" + (int)(restKarori * (time + 1) / 100);
        textPaint.setTextSize(100);
        canvas.drawText(s, screenWidth / 2 - 12 - 25 * s.length(), screenHeight / 2 - 47, textPaint);
        mPaint.setAlpha(255);
        canvas.drawArc(oval,-90,arc * (time+1),false,mPaint);

    }

    public void setRestKarori(int restKarori) {
        if(restKarori>totalKarori)restKarori=totalKarori;
        this.restKarori = this.totalKarori - restKarori;
        this.arc = restKarori * 360 / totalKarori;
        this.arc = arc / 100;
    }

    public void start() {
        init();
        postInvalidate();
    }

    @Override
    public void run() {
        time = 0;
        init();
        while (time<100) {
            postInvalidate();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
        time--;
    }

}

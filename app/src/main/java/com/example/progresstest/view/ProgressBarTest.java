package com.example.progresstest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * @author K
 * @Package: com.example.myapplication
 * @ClassName:
 * @description:
 * @date :2021/7/30 9:45 下午
 */
public class ProgressBarTest extends ProgressBar{
    public ProgressBarTest(Context context) {
        this(context,null);
    }

    public ProgressBarTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    //圆点获取默认颜色
    Paint mPaint;
    //圆点画笔选中颜色
    Paint mPaint2;
    //控件宽度
    int width;
    //控件高度
    int height;
    //平均数,分为几等份,默认为4
    int average = 4;
    //要绘制的圆的半径
    float radius = 15;
    //文字画笔
    TextPaint paintWave;
    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.RED);
        paintWave = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paintWave.setTextSize(18);
        paintWave.setColor(Color.BLUE);
        paintWave.setStrokeWidth(2);
    }

        @Override
        protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            //获取控件宽高
            width = MeasureSpec.getSize(widthMeasureSpec);
            height = MeasureSpec.getSize(heightMeasureSpec);
            //PS:如果使用onMeasure获取控件宽高就必须手动调用setMeasuredDimension方法设置宽高否则会报错
            setMeasuredDimension(width, height);
        }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //由于有开头和结尾,所以是<=
        for (int i = 0; i <= average; i++) {
            //开头需要从起点开始画
            if (i == 0) {
                canvas.drawText(0 + "%", 0 + paintWave.getTextSize() / 2, height / 2 + paintWave.getTextSize() * 3, paintWave);
            }else if (i == average){
                //结尾为了防止画出去,所以是减去整个文字宽度的3倍
                canvas.drawText(100 / average * i + "%", width / average * i - paintWave.getTextSize() * 3, (height >> 1) + paintWave.getTextSize() * 3, paintWave);
            }else {
                canvas.drawText(100 / average * i + "%", width / average * i - paintWave.getTextSize() / 2, (height >> 1) + paintWave.getTextSize() * 3, paintWave);
            }
        }
        //以下还没想好怎么优化
        if (getProgress() ==0 ){
//            canvas.drawCircle(0+radius,height >> 1,radius,mPaint);
            canvas.drawCircle(width/4,height >> 1,radius,mPaint);
            canvas.drawCircle(width/4 * 2,height >> 1,radius,mPaint);
            canvas.drawCircle(width/4 * 3,height >> 1,radius,mPaint);
            canvas.drawCircle(width/4 * 4 - 12,height >> 1,radius,mPaint);
        }else if (getProgress() < 25){
//            canvas.drawCircle(0+radius,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4,height >> 1,radius,mPaint);
            canvas.drawCircle(width/4 * 2,height >> 1,radius,mPaint);
            canvas.drawCircle(width/4 * 3,height >> 1,radius,mPaint);
            canvas.drawCircle(width/4 * 4 - 12,height >> 1,radius,mPaint);
        }else if (getProgress() >= 25 && getProgress() < 50){
//            canvas.drawCircle(0+radius,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4 * 2,height >> 1,radius,mPaint);
            canvas.drawCircle(width/4 * 3,height >> 1,radius,mPaint);
            canvas.drawCircle(width/4 * 4 - 12,height >> 1,radius,mPaint);
        }else if (getProgress() >= 50 && getProgress() < 75){
//            canvas.drawCircle(0+radius,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4 * 2,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4 * 3,height >> 1,radius,mPaint);
            canvas.drawCircle(width/4 * 4 - 12,height >> 1,radius,mPaint);
        }else if (getProgress() >= 75 && getProgress() < 100){
//            canvas.drawCircle(0+radius,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4 * 2,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4 * 3,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4 * 4 - 12,height >> 1,radius,mPaint);
        }else {
//            canvas.drawCircle(0+radius,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4 * 2,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4 * 3,height >> 1,radius,mPaint2);
            canvas.drawCircle(width/4 * 4 - 12,height >> 1,radius,mPaint2);
        }
    }
}

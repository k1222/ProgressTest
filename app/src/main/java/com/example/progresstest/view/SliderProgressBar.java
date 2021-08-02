package com.example.progresstest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.progresstest.R;

/**
 * @author K
 * @Package: com.example.progresstest
 * @ClassName:
 * @description: 带滑块和上\下气泡的进度条
 * @date :2021/8/2 8:18 下午
 */
public class SliderProgressBar extends ProgressBar {
    //控件宽度
    int width;
    //控件高度
    int height;
    //要绘制的圆的半径
    float radius = 15;
    //文字画笔
    TextPaint paintWave;
    //圆点获取默认颜色
    Paint mPaint;
    //文字位置
    int textLocation;
    //是否显示气泡
    boolean isShowBubble;
    public SliderProgressBar(Context context) {
        this(context, null);
    }

    public SliderProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SliderProgressBarStyle);
        textLocation = a.getInteger(R.styleable.SliderProgressBarStyle_textLocation, 0);
        isShowBubble = a.getBoolean(R.styleable.SliderProgressBarStyle_isShowBubble, false);
        init();
    }
    Bitmap bubbleLeft;
    Bitmap bubbleRight;
    int bubbleLeftWidth;
    int bubbleLeftHeight;
    int bubbleRightWidth;
    int bubbleRightHeight;
    private void init() {
        paintWave = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paintWave.setTextSize(18);
        paintWave.setColor(Color.BLUE);
        paintWave.setStrokeWidth(2);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        bubbleLeft = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_bubble_left);
        bubbleRight = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_bubble_right);
        bubbleLeftWidth = bubbleLeft.getWidth();
        bubbleLeftHeight = bubbleLeft.getHeight();
        bubbleRightWidth = bubbleRight.getWidth();
        bubbleRightHeight = bubbleRight.getHeight();
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
        int progress = getProgress();
        float location = width / 100 * progress;
        float textWidth = paintWave.measureText(progress + "%");
        //如果文字初始坐标小于0,则x轴赋值为文字宽度,因为在绘制文字是会减去文字的宽度
        if ((location - textWidth) <= 0){
            location = textWidth;
        }
        //如果文字移动坐标大于控件宽度,则x轴赋值为控件宽度,因为在绘制文字是会减去文字的宽度
        if ((location + textWidth) >= width){
            location = width;
        }
        float textY;
        if (textLocation == 0){
            textY = (height >> 1) + paintWave.getTextSize() * 2;
        }else{
            textY = (height >> 1) - paintWave.getTextSize()  * 2;
        }


        if (progress <= 0){
            canvas.drawCircle(location - radius / 2,height >> 1,radius,mPaint);
        }else {
            canvas.drawCircle(location - radius,height >> 1,radius,mPaint);
        }
        if (isShowBubble) {
            //防止在边界时气泡绘制有问题, 做了一个控件最大宽度判断, 是否为气泡改变方向
            if ((location + bubbleLeftWidth) >= width){
                canvas.drawText(progress + "%", location - bubbleRightWidth /2 - textWidth / 2, height / 2 - bubbleRightHeight / 2, paintWave);
                canvas.drawBitmap(bubbleRight, location - bubbleRightWidth , height / 2 - bubbleRightHeight, null);
            }else {
                canvas.drawText(progress + "%", location + bubbleLeftWidth / 4 - textWidth / 2, height / 2 - bubbleLeftHeight / 2, paintWave);
                canvas.drawBitmap(bubbleLeft, location - bubbleLeftWidth / 4, height / 2 - bubbleLeftHeight, null);
            }
        }else {
            //此处x轴减文字宽度是为了防止在进度为100时文字画出控件边界
            canvas.drawText(progress + "%", location - textWidth, textY, paintWave);
        }

    }
}

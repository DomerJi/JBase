package com.jbase.helper.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by aaa on 2017/8/21.
 */

public class MoreColorText extends View {

    int progressColor;
    Paint textPaint;

    Rect textRect;

    private String progressText;
    public MoreColorText(Context context) {
        super(context);
    }

    public MoreColorText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoreColorText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        progressColor = android.support.v7.appcompat.R.color.notification_icon_bg_color;

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLUE);
        textPaint.setStrokeWidth(1f);
        textPaint.setTextSize(30f);
        textRect = new Rect();

        drawProgressText(canvas);
        drawColorProgressText(canvas);
    }

    /**
     * 进度提示文本
     * @param canvas
     */
    private void drawProgressText(Canvas canvas) {

        textPaint.setColor(Color.BLUE);
        progressText = getProgressText();
        textPaint.getTextBounds(progressText, 0, progressText.length(), textRect);
        int tWidth = textRect.width();
        int tHeight = textRect.height();
        float xCoordinate = (getMeasuredWidth() - tWidth) / 2;
        float yCoordinate = (getMeasuredHeight() + tHeight) / 2;
        canvas.drawText(progressText, xCoordinate, yCoordinate, textPaint);
    }

    /**
     * 变色处理
     * @param canvas
     */
    private void drawColorProgressText(Canvas canvas) {
        textPaint.setColor(Color.RED);
        int tWidth = textRect.width();
        int tHeight = textRect.height();
        float xCoordinate = (getMeasuredWidth() - tWidth) / 2;
        float yCoordinate = (getMeasuredHeight() + tHeight) / 2;
        float progressWidth = (50 / 100) * getMeasuredWidth();
//        if(progressWidth > xCoordinate){
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            float right = Math.min(progressWidth, xCoordinate + tWidth * 1.1f);
            canvas.clipRect(xCoordinate, 0, right, getMeasuredHeight());
            canvas.drawText(progressText, xCoordinate, yCoordinate, textPaint);
            canvas.restore();
//        }
    }

    private String getProgressText(){
        return "冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋冀帅朋";
    }


}

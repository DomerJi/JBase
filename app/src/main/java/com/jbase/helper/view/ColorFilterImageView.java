package com.jbase.helper.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 *
 * @ClassName: ColorFilterImageView
 * @Description: 实现图像根据按下抬起动作变化颜色
 * @author hnclca
 * @date 2016-02-26
 *
 */
public class ColorFilterImageView extends android.support.v7.widget.AppCompatImageView implements OnTouchListener {

    private ColorFilter colorFilter;
    private boolean first = true;

    public ColorFilterImageView(Context context) {
        this(context, null, 0);
    }

    public ColorFilterImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorFilterImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOnTouchListener(this);
    }

    public void setNoOnTouch(){
        setOnTouchListener(null);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:  // 按下时图像变灰
                first = false;
                setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                break;
            case MotionEvent.ACTION_UP:   // 手指离开或取消操作时恢复原色
            case MotionEvent.ACTION_CANCEL:
                if(colorFilter==null){
                    setColorFilter(Color.TRANSPARENT);
                }else {
                    setColorFilter(colorFilter);
                }
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        super.setColorFilter(cf);
        if(cf!=null&&first){
            colorFilter = cf;
        }
        first = false;

    }
}

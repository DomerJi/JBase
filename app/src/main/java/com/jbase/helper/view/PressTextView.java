package com.jbase.helper.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by aaa on 2018/1/24.
 */

public class PressTextView extends android.support.v7.widget.AppCompatTextView {
    public PressTextView(Context context) {
        this(context,null);
    }

    public PressTextView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PressTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        init();
    }

    private void init() {
        setTextColor(createColorStateList(Color.GRAY,Color.GRAY,getTextColors().getDefaultColor()));
    }

    private static ColorStateList createColorStateList(int selected, int pressed, int normal) {
        int[] colors = new int[] { selected, pressed, normal};
        int[][] states = new int[3][];
        states[0] = new int[] { android.R.attr.state_selected};
        states[1] = new int[] { android.R.attr.state_pressed};
        states[2] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

}

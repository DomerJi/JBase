package com.jbase.helper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
    private OnScrollChangedListener onScrollChangedListener;

	public ObservableScrollView(Context context, AttributeSet attrs,
								int defStyle) {
		super(context, attrs, defStyle);
	}

	public ObservableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ObservableScrollView(Context context) {
		super(context);
	}
	
	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if(this.onScrollChangedListener != null) {
			onScrollChangedListener.onScrollChanged(x, y, oldx, oldy);
		}
	}

	public void setMyOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
		this.onScrollChangedListener = onScrollChangedListener;
	}

	public interface OnScrollChangedListener{
		public void onScrollChanged(int l, int t, int oldl, int oldt);
	}

}
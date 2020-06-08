package com.ldy.view.CustomWidget.TextView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class RollingTextView extends TextView {
	
	private ObjectAnimator animator = new ObjectAnimator();
	
	public RollingTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}
	public RollingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	public RollingTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public RollingTextView(Context context) {
		super(context);
		init();
	}
	
	@Override
	public void setScrollX(int value) {
		// TODO Auto-generated method stub
		super.setScrollX(value);
	}
	
	public void init() {
		setSingleLine(true);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setPropertyName("scrollX");
		animator.setInterpolator(new LinearInterpolator());
		animator.setTarget(this);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		CharSequence content = getText();
		float width = Layout.getDesiredWidth(content, getPaint());
		if(getWidth() != 0 && width > getWidth()) {
			if(animator.isRunning()) {
				animator.pause();
			}
			animator.setIntValues(0, (int)width - getWidth());
			animator.setDuration((long) ((width - getWidth()) * 50));
			if(!animator.isStarted()) {
				animator.start();
			} else if(animator.isPaused()) {
				animator.resume();
			}
		} else {
			if(animator.isRunning()) {
				animator.cancel();
			}
		}
	}
}

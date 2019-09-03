package com.ldy.View.dialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.WindowManager.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.ldy.View.CustomWidget.ProcessIcon;

import SRnO.Tool.aar.R;


public class NetProcessDialog extends Dialog {
	private static NetProcessDialog instance;
	private static Handler mHandler = new Handler(Looper.getMainLooper());
	private static String content;
	ObjectAnimator animator;
	public NetProcessDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		init();
	}
	public NetProcessDialog(Context context, int theme) {
		super(context, theme);
		init();
	}
	private NetProcessDialog(Context context) {
		super(context, R.style.NetProcessWindow);
		init();
	}
	public NetProcessDialog(Context context, String content) {
		super(context, R.style.NetProcessWindow);
		this.content=content;
		init();

	}

	public static NetProcessDialog getInstance(final Context context){
		if(Looper.myLooper() == Looper.getMainLooper()) {
			// 主线程
			if(instance == null) {
				instance = new NetProcessDialog(context);
			} else {
				if(((instance.getContext() instanceof ContextThemeWrapper) && ((ContextThemeWrapper)instance.getContext()).getBaseContext() != context) && instance.getContext() != context) {
					if(instance.isShowing()) {
						instance.dismiss();
					}
					instance = new NetProcessDialog(context);
				}
			}
			return instance;
		} else {
			final ConditionVariable cv = new ConditionVariable();
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					if(instance == null) {
						instance = new NetProcessDialog(context);
					} else {
						if(((instance.getContext() instanceof ContextThemeWrapper) && ((ContextThemeWrapper)instance.getContext()).getBaseContext() != context) && instance.getContext() != context) {
							if(instance.isShowing()) {
								instance.dismiss();
							}
							instance = new NetProcessDialog(context);
						}
					}
					cv.open();
				}
			});
			cv.block();
			return instance;
		}
	}
	
	private void init() {
		if(Looper.myLooper() == Looper.getMainLooper()) {
			// 主线程
			initA();
		} else {
			final ConditionVariable cv = new ConditionVariable();
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					initA();
					cv.open();
				}
			});
			cv.block();
		}
	}
	
	private void initA() {
		setContentView(R.layout.net_process_window);
		getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
		LayoutParams lp = getWindow().getAttributes();
		lp.gravity = Gravity.TOP;
		lp.y = 350;
		getWindow().setAttributes(lp);
		setCancelable(false);
	}
	
	@Override
	public void show() {
		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				if(!isShowing()) {
					getWindow().clearFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
					NetProcessDialog.super.show();
					animate();
				}
			}
		});
	}
	
	public void animate() {
		ProcessIcon iv = (ProcessIcon) findViewById(R.id.iv_process_flag);
		TextView tv = (TextView) findViewById(R.id.process_tip);
		if(!TextUtils.isEmpty(content))
		{
			tv.setText(content);
		}
		animator = new ObjectAnimator();
		animator.setFloatValues(0, 1);
		animator.setPropertyName("offset");
		animator.setDuration(3000);
		animator.setInterpolator(new LinearInterpolator());
		animator.setTarget(iv);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setRepeatMode(ValueAnimator.RESTART);
		animator.start();
	}
	
	public void dismiss() {
		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				if(animator!=null && animator.isRunning()) {
					animator.cancel();
				}
				NetProcessDialog.super.dismiss();
			}
		});
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_APP_SWITCH:
			case KeyEvent.KEYCODE_PROG_GREEN:
			case KeyEvent.KEYCODE_MENU:
			case KeyEvent.KEYCODE_HOME:
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
	
}

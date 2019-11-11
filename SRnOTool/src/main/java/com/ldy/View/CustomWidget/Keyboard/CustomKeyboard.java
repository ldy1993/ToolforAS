package com.ldy.View.CustomWidget.Keyboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.FontMetrics;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;


import com.ldy.Utils.UnitUtils;

import java.util.List;

import SRnO.Tool.aar.R;

public class CustomKeyboard extends KeyboardView {
	private static final int DEFAULT_HEIGHT = 450;
	private static CustomKeyboard mCustomKeyboard;
	private WindowManager mWindowManager;
	private LayoutParams lp;
	private boolean isShown = false;
	private int mHeight = DEFAULT_HEIGHT;
	
	private View currentView;
	
	private Keyboard mKeyboard;
	
	private KeyboardEvent mKeyboardEvent;
	
	private boolean willBeep = true;
	
	public void setWillBeep(boolean willBeep) {
		this.willBeep = willBeep;
	}
	
	public View getCurrentView() {
		return currentView;
	}

	public void setCurrentView(View currentView) {
		this.currentView = currentView;
	}

	public static Context context() {
		return mCustomKeyboard == null ? null : mCustomKeyboard.getContext();
	}
	
	public void setKeyboardEvent(KeyboardEvent keyboardEvent) {
		this.mKeyboardEvent = keyboardEvent;
	}
	public static CustomKeyboard getInstance(Context context, AttributeSet attrs) {
		if(mCustomKeyboard == null || context != mCustomKeyboard.getContext()) {
			mCustomKeyboard = new CustomKeyboard(context, attrs);
		}
		return mCustomKeyboard;
	}
	
	public static enum KEYBOARD_TYPE {
		/**
		 * 简单键盘类型
		 */
		KEYBOARD_TYPE_SIMPLE,
		;
	}
	
	private KEYBOARD_TYPE mKeyboardType = KEYBOARD_TYPE.KEYBOARD_TYPE_SIMPLE;

	@SuppressLint("NewApi")
	public CustomKeyboard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}
	public CustomKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	public CustomKeyboard(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		mWindowManager = ((Activity)getContext()).getWindowManager();
		lp = new LayoutParams();
		lp.width = LayoutParams.MATCH_PARENT;
		lp.height = mHeight;
		lp.windowAnimations = android.R.style.Animation_InputMethod;
		lp.gravity = Gravity.BOTTOM;
		lp.flags |= LayoutParams.FLAG_NOT_TOUCH_MODAL;
		lp.flags |= LayoutParams.FLAG_NOT_FOCUSABLE;
		lp.type = LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
		initKeyboard();
		setPreviewEnabled(false);
	}
	
	public int getCustomHeight() {
		return mHeight;
	}
	
	@Override
	public void setOnKeyboardActionListener(final OnKeyboardActionListener listener) {
		OnKeyboardActionListener proxy = new OnKeyboardActionListener() {
			private OnKeyboardActionListener l = listener;
			@Override
            public void swipeUp() {
				l.swipeUp();
			}
			@Override
            public void swipeRight() {
				l.swipeRight();
			}
			@Override
            public void swipeLeft() {
				l.swipeLeft();
			}
			@Override
            public void swipeDown() {
				l.swipeDown();
			}
			@Override
            public void onText(CharSequence text) {
				l.onText(text);
			}
			@Override
            public void onRelease(int primaryCode) {
				l.onRelease(primaryCode);
			}
			@Override
            public void onPress(int primaryCode) {
				l.onPress(primaryCode);
			}
			@Override
            public void onKey(int primaryCode, int[] keyCodes) {
				if(willBeep) {
//					Device.beepPromt(getContext());
				}
				l.onKey(primaryCode, keyCodes);
			}
		};
		super.setOnKeyboardActionListener(proxy);
	}
	
	public void setKeyboardType(KEYBOARD_TYPE keyboardType) {
		mKeyboardType = keyboardType;
		initKeyboard();
	}
	
	public void initKeyboard() {
		switch (mKeyboardType) {
		case KEYBOARD_TYPE_SIMPLE:
			mHeight = (int) UnitUtils.dp2px(250);
			mKeyboard = new Keyboard(getContext(), R.xml.keyboard_type_simple, 0, getContext().getResources().getDisplayMetrics().widthPixels, mHeight);
			break;
		default:
			break;
		}
		setKeyboard(mKeyboard);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRGB(0xdb, 0xdb, 0xdb);
		List<Key> keys = mKeyboard.getKeys();
		for(Key key : keys) {
			switch (mKeyboardType) {
			case KEYBOARD_TYPE_SIMPLE:
				drawSimpleKeyboard(key, canvas);
				break;

			default:
				break;
			}
		}
	}
	
	private void drawSimpleKeyboard(Key key, Canvas canvas) {
		switch (key.codes[0]) {
		case 48:
		case 49:
		case 50:
		case 51:
		case 52:
		case 53:
		case 54:
		case 55:
		case 56:
		case 57:
			drawKeyBackground(R.drawable.simple_key_bg, canvas, key);
			drawKeyContent(canvas, key);
			break;
		case -3:
			drawKeyBackground(R.drawable.simple_key_bg, canvas, key);
			drawForground(R.drawable.icon_key, canvas, key);
			break;
		case -5:
			drawKeyBackground(R.drawable.simple_key_bg, canvas, key);
			drawForground(R.drawable.icon_delete, canvas, key);
			break;
		default:
			break;
		}
	}
    
    private void drawKeyBackground(int drawableId, Canvas canvas, Key key) {
        Drawable npd = (Drawable) getContext().getResources().getDrawable(drawableId, null);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            npd.setState(drawableState);
        }
        npd.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        npd.draw(canvas);
    }
    
    private void drawForground(int drawableId, Canvas canvas, Key key) {
        Drawable npd = (Drawable) getContext().getResources().getDrawable(drawableId, null);
        npd.setBounds(key.x + (key.width - npd.getMinimumWidth()) / 2, key.y + (key.height - npd.getMinimumHeight()) / 2, key.x + key.width - (key.width - npd.getMinimumWidth()) / 2, key.y + key.height - (key.height - npd.getMinimumHeight()) / 2);
        npd.draw(canvas);
    }
    
    private void drawKeyContent(Canvas canvas, Key key) {
    	String text;
    	if(key.codes[0] >= 48 && key.codes[0] <= 57) {
    		// number
    		text = String.valueOf(key.codes[0] - 48);
    		TextPaint paint = new TextPaint();
    		paint.setTextSize((float) UnitUtils.dp2px(25));
    		paint.setAntiAlias(true);
    		paint.setColor(Color.rgb(0x33, 0x33, 0x33));
    		float textWidth = paint.measureText(text);
    		int txtX = (int) (key.x + (key.width - textWidth) / 2);
    		FontMetrics metrics = paint.getFontMetrics();
    		int txtY = (int) (key.y + key.height / 2 + (metrics.bottom - metrics.top) / 2 - metrics.bottom);
    		canvas.drawText(text, txtX, txtY, paint);
    	} else {
    		
    	}
    }
	
	
	public void show() {
		if(!isShown) {
			isShown = true;
			if(mKeyboardEvent != null) {
				mKeyboardEvent.onShow();
			}
			if(getParent() == null) {
				try {
					mWindowManager.addView(this, lp);
				}catch (Exception e)
				{
					e.printStackTrace();
				}
				setVisibility(View.VISIBLE);
			} else {
				setVisibility(View.VISIBLE);
			}
		}
	}
	
	@Override
    public boolean isShown() {
		return isShown;
	}
	
	public void hide() {
		if(isShown) {
			isShown = false;
			if(getParent() != null) {
				setVisibility(View.GONE);
				if(mKeyboardEvent != null) {
					Log.e("ldy","hide");
					mKeyboardEvent.onHide();
				}
			}
		}
	}
	
	public void detach() {
		if(isAttachedToWindow()) {
			mWindowManager.removeView(this);
			if(mKeyboardEvent != null) {
				mKeyboardEvent.onHide();
			}
		}
	}
	
	public static interface KeyboardEvent {
		public void onShow();
		public void onHide();
	}
	
//	private NavigationBarGesture gesture;
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent event) {
//		if(gesture == null) gesture = new NavigationBarGesture(getContext());
//		if(gesture.onInterceptEvent(event) == true) {
//			return true;
//		} else {
//			return super.dispatchTouchEvent(event);
//		}
//	}
}

package com.ldy.View.CustomWidget.Keyboard.EdittextInput;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ldy.View.CustomWidget.Keyboard.CustomKeyboard;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EditTextInput extends EditText {

	@SuppressLint("NewApi")
	public EditTextInput(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		disableSystemInput();
	}
	public EditTextInput(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		disableSystemInput();
	}
	public EditTextInput(Context context, AttributeSet attrs) {
		super(context, attrs);
		disableSystemInput();
	}
	public EditTextInput(Context context) {
		super(context);
		disableSystemInput();
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean superResult = super.onTouchEvent(event);
		if(event.getAction() == MotionEvent.ACTION_UP) {
			CustomKeyboard customKeyboard = CustomKeyboard.getInstance(getContext(), null);
			customKeyboard.setCurrentView(this);
			if(!customKeyboard.isShown()) {
				customKeyboard.show();
			}
			customKeyboard.setOnKeyboardActionListener(keyboardActionListener);
		}
		return superResult;
	}
	
	@Override
    protected void onFocusChanged(boolean focused, int direction, android.graphics.Rect previouslyFocusedRect) {

		try {
			if(focused) {
				CustomKeyboard customKeyboard = CustomKeyboard.getInstance(getContext(), null);
				customKeyboard.setCurrentView(this);
				if(!customKeyboard.isShown()) {
					customKeyboard.show();
				}
				customKeyboard.setOnKeyboardActionListener(keyboardActionListener);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
	};
	
	private OnKeyboardActionListener keyboardActionListener = new OnKeyboardActionListener() {
		@Override
		public void swipeUp() {
		}
		@Override
		public void swipeRight() {
		}
		@Override
		public void swipeLeft() {
		}
		@Override
		public void swipeDown() {
		}
		@Override
		public void onText(CharSequence text) {
            Editable editable = getText();
            int start = getSelectionStart();
            String temp = editable.subSequence(0, start) + text.toString()
                    + editable.subSequence(start, editable.length());
            setText(temp);
            Editable etext = getText();
            Selection.setSelection(etext, start + 1);
		}
		@Override
		public void onRelease(int primaryCode) {
		}
		@Override
		public void onPress(int primaryCode) {
		}
		@Override
		public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = getText();
            int start = getSelectionStart();
			CustomKeyboard customKeyboard = CustomKeyboard.getInstance(getContext(), null);
            if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 键盘关闭
            	customKeyboard.hide();
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 删除
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else if (primaryCode == 57419) {// 000
                editable.insert(start, "000");
            } else if (primaryCode == 57418) {// 取消
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new Instrumentation().sendKeyDownUpSync(57418);
                    }
                }).start();
            } else if (primaryCode == 57420) {// confirm
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
                    }
                }).start();
            } else if (primaryCode == 57421) {// 00
                editable.append("00");
            } else if (primaryCode == 57422) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new Instrumentation().sendKeyDownUpSync(57422);
                    }
                }).start();
            } else if (primaryCode != -1) {	// -1表示键盘占位，表空值
                editable.insert(start, Character.toString((char) primaryCode));
            }
		}
	};
	
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		if(changedView == this && visibility == View.GONE) {
			CustomKeyboard.getInstance(getContext(), null).hide();
		}
	}
	
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if(CustomKeyboard.getInstance(getContext(), null).isAttachedToWindow()) {
			CustomKeyboard.getInstance(getContext(), null).detach();
		}
	}
	
	
    /***
     * 
     * 
     * @param edit
     * @return 判断输入法是否打开
     */
    private void disableSystemInput() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();// is若返回true，则表示输入法打开
        if (isOpen) {
            if (imm.hideSoftInputFromWindow(getWindowToken(), 0)) {
				;
			}
        }
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }

        if (methodName == null) {
            setInputType(InputType.TYPE_NULL);
        } else {
            ((Activity)getContext()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(this, false);
            } catch (NoSuchMethodException e) {
                this.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

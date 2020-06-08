package com.ldy.view.CustomWidget.TextView;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;


import com.ldy.custom.view.lib.R;
import com.ldy.utils.ReflactUtils;
import com.ldy.utils.UnitUtils;


public class PopSpinner extends TextView {
	private PopupWindow window;
	private String fieldName;
	private Object selected;
	private int selectedIndex;
	private ListAdapter adapter;
	private static Context ConText;
	private  OnItemSelectedListener listener;
	public  void setOnItemSelectedListener(OnItemSelectedListener listeners) {
		this.listener = listeners;
	}
	public static interface OnItemSelectedListener{
		public void onItemSelected(PopSpinner spinner, int position);
	}
	public PopSpinner(Context context, AttributeSet attrs) {
		
		super(context, attrs);
		ConText=context;
		init();
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PopSpinner);
		CharSequence[] values = array.getTextArray(R.styleable.PopSpinner_values);
		if(values != null) {
			this.adapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.just_textview, R.id.just_textview, values);
			setAdapter(adapter);
		}
		array.recycle();
	}
	private void init() {
		setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.icon_more_pre), null);
		setCompoundDrawablePadding((int) UnitUtils.dp2px(10));
//		setGravity(Gravity.CENTER_VERTICAL);
		setGravity(Gravity.RIGHT);
		window = new PopupWindow(getContext());
		window.setContentView(LayoutInflater.from(getContext()).inflate(R.layout.pop_list, null));
		window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		window.setBackgroundDrawable(new BitmapDrawable());
		window.setOutsideTouchable(true);
		window.setAnimationStyle(android.R.style.Animation_InputMethod);
		window.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					window.dismiss();
					return true;
				} else {
					return false;
				}
			}
		});
		((ListView)window.getContentView().findViewById(R.id.pop_list)).setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Object obj = parent.getItemAtPosition(position);
				setSelection(obj);
				selectedIndex = position;
				window.dismiss();
				if(listener != null) {
					listener.onItemSelected(PopSpinner.this, position);
				}
			}
		});
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//按键时隐藏输入键盘byldy.20180529
				InputMethodManager imm = (InputMethodManager) ConText.
					getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);;
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				showList();
			}
		});	
		window.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				changeWindowAlpha(1);
			}
		});
	}
	
	public void setSelectedIndex(int index) {
		if(index >= 0 && index < adapter.getCount()) {
			selectedIndex = index;
			selected = adapter.getItem(index);
			setText();
			if(listener != null) {
				listener.onItemSelected(this, selectedIndex);
			}
		}
	}
	
	public void setSelection(Object selection) {
		for(int i = 0; i < adapter.getCount(); i ++) {
			if(adapter.getItem(i).equals(selection)) {
				selectedIndex = i;
				selected = selection;
				setText();
				if(listener != null) {
					listener.onItemSelected(this, selectedIndex);
				}
			}
		}
	}
	
	public Object getSelected() {
		return selected;
	}
	
	public int getSelectedIndex() {
		return selectedIndex;
	}
	
	private void changeWindowAlpha(float alpha) {
		WindowManager.LayoutParams lp = ((Activity)getContext()).getWindow().getAttributes();
		lp.alpha = alpha;
		((Activity)getContext()).getWindow().setAttributes(lp);
	}
	
	
	private void setText() {
		if(fieldName == null || fieldName.isEmpty()) {
			setText(selected.toString());
		} else {
			setText(ReflactUtils.<String>getFieldValue(selected.getClass(), selected, fieldName));
		}
	}
	
	public void showList() {
		changeWindowAlpha(0.5f);
		window.showAtLocation(getRootView(), Gravity.BOTTOM, 0, 0);
	}
	
	@Override
	protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
		if(!focused) {
			if(window != null && window.isShowing()) {
				window.dismiss();
			}
		}
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public void setAdapter(ListAdapter adapter) {
		this.adapter = adapter;
		setSelection(adapter.getItem(0));
		selectedIndex = 0;
		((ListView)window.getContentView().findViewById(R.id.pop_list)).setAdapter(adapter);
	}
	
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if(window != null && window.isShowing()) {
			window.dismiss();
		}
	}

}

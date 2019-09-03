package com.ldy.View.popupWindow;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.PopupWindow;

import SRnO.Tool.aar.R;

public class PopupPasswordKeyboard extends PopupWindow {
	
	private Context context;
	
	public PopupPasswordKeyboard(Context context) {
		super();
		this.context = context;
		setContentView(LayoutInflater.from(context).inflate(R.layout.popup_password_keyboard, null));
		setTouchable(true);
		setOutsideTouchable(true);
	}
	
	public void show() {
		super.showAtLocation(((Activity)context).findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
	}
}

package com.ldy.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.ldy.custom.view.lib.R;


public class IDDialog extends Dialog {
	public IDDialog(Context context, String id) {
		super(context);
		init(id);
	}
	
	private void init(String id) {
		setContentView(R.layout.id_dialog);
		getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
		LayoutParams lp = getWindow().getAttributes();
		lp.gravity = Gravity.TOP;
		lp.y = 350;
		getWindow().setAttributes(lp);
		setCancelable(false);
		((TextView)findViewById(R.id.tv_id)).setText(id);
		findViewById(R.id.bt_confirm).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
				if(mOnButtonListener != null) {
					mOnButtonListener.onConfirm();
				}
			}
		});
	}
	public static interface OnButtonListenerID {
		public void onConfirm();
	}
	private OnButtonListenerID mOnButtonListener;
	
	public void setOnButtonListenerID(OnButtonListenerID mOnButtonListener) {
		this.mOnButtonListener = mOnButtonListener;
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_APP_SWITCH:
			case KeyEvent.KEYCODE_PROG_GREEN:
			case KeyEvent.KEYCODE_MENU:
			case KeyEvent.KEYCODE_HOME:
			return true;
			default:
		}
		return super.dispatchKeyEvent(event);
	}
}

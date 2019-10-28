package com.ldy.View.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import SRnO.Tool.aar.R;


public class AlertDialog extends Dialog {
	public AlertDialog(Context context, String alert) {
		super(context);
		init(alert);
	}

	public static interface OnButtonListener {
		public void onConfirm();

		public void onCancel();
	}

	private OnButtonListener mOnButtonListener;

	public void setOnButtonListener(OnButtonListener mOnButtonListener) {
		this.mOnButtonListener = mOnButtonListener;
	}

	private void init(String alert) {
		setContentView(R.layout.alert_dialog);
		getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
		LayoutParams lp = getWindow().getAttributes();
		lp.gravity = Gravity.TOP;
		lp.y = 350;
		getWindow().setAttributes(lp);
		setCancelable(false);
		((TextView) findViewById(R.id.tv_alert)).setText(alert);
		findViewById(R.id.bt_confirm).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dismiss();
						if (mOnButtonListener != null) {
							mOnButtonListener.onConfirm();
						}
					}
				});
		findViewById(R.id.bt_cancel).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dismiss();
						if (mOnButtonListener != null) {
							mOnButtonListener.onCancel();
						}
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
			default:
		}
		return super.dispatchKeyEvent(event);
	}
}

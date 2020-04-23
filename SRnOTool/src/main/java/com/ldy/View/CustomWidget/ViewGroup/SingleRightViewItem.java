package com.ldy.View.CustomWidget.ViewGroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ldy.Utils.UnitUtils;

import SRnO.Tool.aar.R;


public class SingleRightViewItem extends LeftDragItem {
	private OnRightViewClickedListener mOnRightViewClickedListener;
	private TextView d ;
	public final static int RIGHT_VIEW_WIDTH_DEFAULT = 150;
	
	public static interface OnRightViewClickedListener {
		public void onClicked();
	}
	
	public void setOnRightViewClickedListener(OnRightViewClickedListener listener) {
		this.mOnRightViewClickedListener = listener;
		rightView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnRightViewClickedListener.onClicked();
			}
		});
	}
	
	public SingleRightViewItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(attrs);
	}
	public SingleRightViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}
	public SingleRightViewItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}
	public SingleRightViewItem(Context context) {
		super(context);
		init(null);
	}
	public void SetText(String Text) {
		d.setText(Text);
		setRightView(d);
	}
	public void init(AttributeSet attrs) {
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SingleRightViewItem);
		float rightViewWidth = a.getDimension(R.styleable.SingleRightViewItem_right_view_width, RIGHT_VIEW_WIDTH_DEFAULT);
		float textSize = a.getDimension(R.styleable.SingleRightViewItem_right_view_text_size, (float) UnitUtils.px2dp(getResources().getDimension(R.dimen.txt_small)));
		String text = a.getString(R.styleable.SingleRightViewItem_right_view_text);
		int textColor = a.getColor(R.styleable.SingleRightViewItem_right_view_text_color, Color.WHITE);
		int backColor = a.getColor(R.styleable.SingleRightViewItem_right_view_color, Color.RED);
		a.recycle();
		d= new TextView(getContext());
		d.setGravity(Gravity.CENTER);
		d.setText(text);
		d.setBackgroundColor(backColor);
		d.setTextColor(textColor);
		d.setTextSize(textSize);
		d.setMinimumWidth((int) rightViewWidth);
		d.setMaxWidth((int) rightViewWidth);
		setRightView(d);
	}
}

package com.ldy.View.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import SRnO.Tool.aar.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditDialog extends Dialog {
	private Context context;
	   //过滤表情正则表达式
    public  String EMOJI = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
    
	public EditDialog(Context context, String provinceName) {
		super(context);
		this.context=context;
		init(provinceName);
	}

	private void init(String provinceName) {
		setContentView(R.layout.edit_dialog);
		getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
		LayoutParams lp = getWindow().getAttributes();
		lp.gravity = Gravity.TOP;
		lp.y = 350;
		getWindow().setAttributes(lp);
		setCancelable(true);
		((TextView)findViewById(R.id.tv_address)).setText(provinceName);
		 InputFilter filter = new InputFilter() {
				@Override
				public CharSequence filter(CharSequence arg0, int arg1,
						int arg2, Spanned arg3, int arg4, int arg5) {
					// TODO Auto-generated method stub
					 if (" ".equals(arg0) || arg0.toString().contentEquals("\n")) {
			                return "";
			            } else {

							 if(arg0.length()>0){
				                    if(isMatcher(arg0.toString(),EMOJI)){

										Toast.makeText(context, "不允许输入表情",Toast.LENGTH_SHORT).show();
				                        return "";
				                    }else{
				                    
				                    	 int keep = 15 - (arg3.length() - (arg5 - arg4));
			                             if (keep <= 0) {
											 Toast.makeText(context, "输入长度超限",Toast.LENGTH_SHORT).show();
			                                   return "";
			                             } 
			                             else if (arg0.length() + arg3.length()>15) {
											 Toast.makeText(context, "输入长度超限",Toast.LENGTH_SHORT).show();

											 return "";
			                           }
			                             else if (keep >= arg5 - arg4) {
			                                   return null;
			                             }else {
											 Toast.makeText(context, "输入长度超限",Toast.LENGTH_SHORT).show();

											 return "";
			                             }

				                    }
				                }
				                return null;
						
			            }
				}
		    };
		((EditText)findViewById(R.id.et_name)).setFilters(new InputFilter[]{filter});
		findViewById(R.id.bt_upconfirm).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!"".equals(((EditText) findViewById(R.id.et_name)).getText().toString()))
				{
				dismiss();
				if(mOnButtonListener != null) {
					mOnButtonListener.onConfirm(((EditText) findViewById(R.id.et_name)).getText().toString());
				}
				}else
				{
					Toast.makeText(context, "输入内容不能为空",Toast.LENGTH_SHORT).show();

				}
			}
		});
		findViewById(R.id.bt_upcancel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
				mOnButtonListener.onUpcancel();
			}
		});
	}
	public static interface OnButtonListenerID {
		public void onConfirm(String name);
		public void onUpcancel();
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
	 /**
     * 正则表达式匹配
     * @param content 待匹配内容
     * @param regex   正则表达式
     * @return true 匹配成功
     */
    public static boolean isMatcher(String content,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }
}

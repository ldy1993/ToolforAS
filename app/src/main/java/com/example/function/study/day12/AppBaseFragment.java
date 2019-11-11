package com.example.function.study.day12;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import com.ldy.View.CustomWidget.Keyboard.CustomKeyboard;
import com.ldy.function.Log.Log;

import me.jerry.framework.android.FragmentFrame;

public abstract class AppBaseFragment extends FragmentFrame {
	@Override
	public void onPause() {
		super.onPause();
	}
	@SuppressLint("ServiceCast")
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if(hidden) {
//			if(CustomKeyboard.context() != null && CustomKeyboard.context() == getActivity()) {
//				CustomKeyboard.getInstance(getActivity(), null).hide();
//			}
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
		} else {
			onShow();
		}
	}
	
	@Override
	public void onDestroyView() {
		if(CustomKeyboard.context() != null && CustomKeyboard.context() == getActivity()) {
			CustomKeyboard.getInstance(getActivity(), null).hide();
		}
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
		Log.e("ldy", "");
		super.onDestroyView();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if(!isHidden()) {
			onShow(); 
//			if (TextUtils.isEmpty(BankApplication.province)) {
//				BankApplication.getLocation(getActivity());
//			}
		}
	}
	
//	public AppActivity getAppActivity() {
//		return (AppActivity) getActivity();
//	}
	
	/** same as {@link android.app.Activity#onResume()} **/
	protected void onShow(){}
}

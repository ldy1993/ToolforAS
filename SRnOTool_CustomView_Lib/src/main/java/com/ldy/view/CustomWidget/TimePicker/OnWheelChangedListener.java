
package com.ldy.view.CustomWidget.TimePicker;

public interface OnWheelChangedListener {
	/**
	 * 当前项改变时的回调方法
	 * 
	 * @param wheel
	 * @param oldValue
	 * @param newValue
	 */
	void onChanged(WheelView wheel, int oldValue, int newValue);
}

package com.ldy.View.CustomWidget.TimePicker;

import android.view.View;


import com.ldy.View.CustomWidget.TimePicker.NumericWheelAdapter;
import com.ldy.View.CustomWidget.TimePicker.OnWheelChangedListener;

import SRnO.Tool.aar.R;

import java.util.Arrays;
import java.util.List;

public class WheelMain {

	private View view;
	private WheelView wv_year;
	private WheelView wv_month;
	private WheelView wv_day;
	private WheelView wv_hours;
	private WheelView wv_mins;
	private WheelView wv_second;
	private int START_YEAR = 2015, END_YEAR;

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public int getSTART_YEAR() {
		return START_YEAR;
	}

	public void setSTART_YEAR(int sTART_YEAR) {
		START_YEAR = sTART_YEAR;
	}

	public int getEND_YEAR() {
		return END_YEAR;
	}

	public void setEND_YEAR(int eND_YEAR) {
		END_YEAR = eND_YEAR;
	}

	public WheelMain(View view) {
		super();
		this.view = view;
		setView(view);
	}

	public WheelMain(View view, boolean hasSelectTime) {
		super();
		this.view = view;
		setView(view);
	}

	public void initDateTimePicker(int year, int month, int day) {
		this.initDateTimePicker(year, month, day, -1, -1, -1);
	}

	/**
	 * 加载时间选择器,可选显示，不使用传-1
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param h
	 * @param m
	 */
	public void initDateTimePicker(int year, int month, int day, int h, int m, int s) {
		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_month = (WheelView) view.findViewById(R.id.month);
		wv_day = (WheelView) view.findViewById(R.id.day);
		wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_mins = (WheelView) view.findViewById(R.id.min);
		wv_second = (WheelView) view.findViewById(R.id.second);

		// 年
		if (year != -1) {
			wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
			wv_year.setCyclic(true);// 可循环滚动
			wv_year.setLabel("年");// 添加文字
			wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

			// 添加"年"监听
			OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
				@Override
                public void onChanged(WheelView wheel, int oldValue, int newValue) {
					int year_num = newValue + START_YEAR;
					// 判断大小月及是否闰年,用来确定"日"的数据
					if (list_big.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 31));
					} else if (list_little.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 30));
					} else {
						if ((year_num % 4 == 0 && year_num % 100 != 0) || year_num % 400 == 0) {
                            wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                        } else {
                            wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                        }
					}
				}
			};
			wv_year.addChangingListener(wheelListener_year);
		} else {
			wv_year.setVisibility(View.GONE);
		}

		// 月
		if (month != -1) {
			wv_month.setAdapter(new NumericWheelAdapter(1, 12));
			wv_month.setCyclic(true);
			wv_month.setLabel("月");
			wv_month.setCurrentItem(month);

			// 添加"月"监听
			OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
				@Override
                public void onChanged(WheelView wheel, int oldValue, int newValue) {
					int month_num = newValue + 1;
					// 判断大小月及是否闰年,用来确定"日"的数据
					if (list_big.contains(String.valueOf(month_num))) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 31));
					} else if (list_little.contains(String.valueOf(month_num))) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 30));
					} else {
						if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year.getCurrentItem() + START_YEAR) % 100 != 0) || (wv_year.getCurrentItem() + START_YEAR) % 400 == 0) {
                            wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                        } else {
                            wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                        }
					}
				}
			};
			wv_month.addChangingListener(wheelListener_month);
		} else {
			wv_month.setVisibility(View.GONE);
		}

		// 日
		if (day != -1) {
			wv_day.setCyclic(true);
			// 判断大小月及是否闰年,用来确定"日"的数据
			if (list_big.contains(String.valueOf(month + 1))) {
				wv_day.setAdapter(new NumericWheelAdapter(1, 31));
			} else if (list_little.contains(String.valueOf(month + 1))) {
				wv_day.setAdapter(new NumericWheelAdapter(1, 30));
			} else {
				// 闰年
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                } else {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                }
			}
			wv_day.setLabel("日");
			wv_day.setCurrentItem(day - 1);
		} else {
			wv_day.setVisibility(View.GONE);
		}

		// 时
		if (h != -1) {
			wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
			wv_hours.setCyclic(true);
			wv_hours.setLabel("时");
			wv_hours.setCurrentItem(h);
		} else {
			wv_hours.setVisibility(View.GONE);
		}

		// 分
		if (h != -1) {
			wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
			wv_mins.setCyclic(true);
			wv_mins.setLabel("分");
			wv_mins.setCurrentItem(m);
		} else {
			wv_mins.setVisibility(View.GONE);
		}

		// 秒
		if (s != -1) {
			wv_second.setAdapter(new NumericWheelAdapter(0, 59));
			wv_second.setCyclic(true);
			wv_second.setLabel("秒");
			wv_second.setCurrentItem(s);
		} else {
			wv_second.setVisibility(View.GONE);
		}

	}

	/**
	 * 获得选中时间
	 * 
	 * @param strYear
	 *            间开符号
	 * @param strMon
	 * @param strDay
	 * @param strHour
	 * @param strMins
	 * @param strSecond
	 * @return
	 */
	public String getTime(String strYear, String strMon, String strDay, String strHour, String strMins, String strSecond) {
		StringBuffer sb = new StringBuffer();
		String year = "";
		String mon = "";
		String day = "";
		String hour = "";
		String mins = "";
		String second = "";

		if (wv_year.getVisibility() != View.GONE) {
			year = String.valueOf(wv_year.getCurrentItem() + START_YEAR);
			year = new StringBuffer(year + strYear).toString();
		}
		if (wv_month.getVisibility() != View.GONE) {
			mon = String.valueOf(wv_month.getCurrentItem() + 1);
			if (wv_month.getCurrentItem() + 1 <= 9) {
				mon = new StringBuffer("0" + mon).toString(); // 前面加0
			}
			mon = new StringBuffer(mon + strMon).toString();
		}
		if (wv_day.getVisibility() != View.GONE) {
			day = String.valueOf(wv_day.getCurrentItem() + 1);
			if (wv_day.getCurrentItem() + 1 <= 9) {
				day = new StringBuffer("0" + day).toString();
			}
			day = new StringBuffer(day + strDay).toString();
		}
		if (wv_hours.getVisibility() != View.GONE) {
			hour = String.valueOf(wv_hours.getCurrentItem());
			if (wv_hours.getCurrentItem() <= 9) {
				hour = new StringBuffer("0" + hour).toString();
			}
			hour = new StringBuffer(hour + strHour).toString();
		}
		if (wv_mins.getVisibility() != View.GONE) {
			mins = String.valueOf(wv_mins.getCurrentItem());
			if (wv_mins.getCurrentItem() <= 9) {
				mins = new StringBuffer("0" + mins).toString();
			}
			mins = new StringBuffer(mins + strMins).toString();
		}
		if (wv_second.getVisibility() != View.GONE) {
			second = String.valueOf(wv_second.getCurrentItem());
			if (wv_second.getCurrentItem() <= 9) {
				second = new StringBuffer("0" + second).toString();
			}
			second = new StringBuffer(second + strSecond).toString();
		}

		sb.append(year).append(mon).append(day).append(hour).append(mins).append(second);
		return sb.toString();
	}
}

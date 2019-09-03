package com.ldy.View.CustomWidget.TimePicker;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/5/20
 * 描    述：
 * 修订历史：
 * ================================================
 */

import java.util.Calendar;
import java.util.Date;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.ldy.View.dialog.IOSAlertDialog;

import SRnO.Tool.aar.R;

@SuppressLint("SimpleDateFormat")
public class TimeUtils {

    private  Context context;
    private static WheelMain wheelMain;
    //    private Context context;
//    private WheelMain wheelMain;

    public TimeUtils(Context context) {
        super();
        this.context = context;
    }

    /**
     * 获得选中的时间
     * @param strYear 间隔符号
     * @param strMon
     * @param strDay
     * @param strHour
     * @param strMins
     * @param strSecond
     * @return
     */
    public static String getTxtTime(String strYear, String strMon, String strDay, String strHour, String strMins, String strSecond) {
        return wheelMain.getTime(strYear, strMon, strDay, strHour, strMins, strSecond);
    }

    public View timePickerView() {
        View timepickerview = View.inflate(context, R.layout.timepicker, null);
        wheelMain = new WheelMain(timepickerview);
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        wheelMain.setEND_YEAR(year);// 设置最大年份
        wheelMain.initDateTimePicker(year, month, day, hour, min, second);

        return timepickerview;
    }

    /**
     * 时间选择控件
     *
     * @param date
     *            需显示的日期
     * @return
     */
    public static View timePickerView(Date date ,Context context) {
        View timepickerview = View.inflate(context, R.layout.timepicker, null);
        wheelMain = new WheelMain(timepickerview);
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // int hour = calendar.get(Calendar.HOUR_OF_DAY);
        // int min = calendar.get(Calendar.MINUTE);
        // int second = calendar.get(Calendar.SECOND);
        wheelMain.setEND_YEAR(year);
        // 若为空显示当前时间
        if (date != null && !date.equals("")) {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
//                Date date = format.parse(dateStr);
                calendar.setTime(date);
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                wheelMain.initDateTimePicker(year, month, day, -1, -1, -1);// 传-1表示不显示
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            wheelMain.initDateTimePicker(year, month, day, -1, -1, -1);
        }
        return timepickerview;
    }

    /**
     * alertDialog时间选择
     *
     * @param date
     */
    public static void timePickerAlertDialog(Date date,Context context, OnClickListener CancelListener, OnClickListener ComfirmListener) {
        IOSAlertDialog dialog = new IOSAlertDialog(context);
        dialog.builder();
        dialog.setTitle("选择日期");
        dialog.setView(timePickerView(date, context));
        dialog.setNegativeButton("取消", CancelListener);

        dialog.setPositiveButton("确定", ComfirmListener);
        dialog.show();
    }
}


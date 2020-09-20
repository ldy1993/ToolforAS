package com.example.function.study.B_界面视图.F_对话框Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu.SonMenuEnum;
import com.ldy.study.R;

import java.util.ArrayList;
@MenuActivity(menu= MenuEnum.学习功能主菜单,sonMenu = SonMenuEnum.对话框)
public class Day9_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day9);

    }
    //普通Dialog
    public void alertDialog_bt1(View view)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.ic_category_0);
        builder.setTitle("alert对话框");
        builder.setMessage("这是两按钮的普通对话框");
        builder.setPositiveButton("按钮1", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Day9_Activity.this, "按钮1被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("按钮2", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Day9_Activity.this, "按钮2被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    //列表Dialog
    public void alertDialog_bt2(View view)
    {
        final String[] items = { "富强","民主","科学","自由" };
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("我是一个列表对话框");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Day9_Activity.this, "按钮"+ items[which]+"被点击了", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Day9_Activity.this, "取消了", Toast.LENGTH_SHORT).show();

            }
        });
        builder.show();
    }
    //单选对话框
    public void alertDialog_bt3(View view)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final String[] items = { "富强","民主","科学","自由" };
        builder.setTitle("alert对话框");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Day9_Activity.this, "按钮"+ items[which]+"被点击了", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Day9_Activity.this, "确认", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Day9_Activity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    //多选对话框
    ArrayList<Integer> Choices = new ArrayList<>();
    public void alertDialog_bt4(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items = {"富强", "民主", "科学", "自由"};
        final boolean Boolean[] = {false, false, false, false};
        builder.setTitle("alert对话框");
        builder.setMultiChoiceItems(items, Boolean, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which,
                                boolean isChecked) {
                if (isChecked) {
                    Choices.add(which);
                } else {
                    Choices.remove(which);
                }
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = Choices.size();
                String str = "";
                for (int i = 0; i < size; i++) {
                    str += items[Choices.get(i)] + " ";
                }

                Toast.makeText(Day9_Activity.this, "确认" + str, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Day9_Activity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    //等待dialog
    public void alertDialog_bt5(View view) {
        /* 等待Dialog具有屏蔽其他控件的交互能力
         * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
         * 下载等事件完成后，主动调用函数关闭该Dialog
         */
        ProgressDialog waitingDialog=
                new ProgressDialog(Day9_Activity.this);
        waitingDialog.setTitle("我是一个等待Dialog");
        waitingDialog.setMessage("等待中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(true);
        waitingDialog.show();
    }
    //进度条dialog
    public void progressDialog_bt6(View view) {
        /* @setProgress 设置初始进度
         * @setProgressStyle 设置样式（水平进度条）
         * @setMax 设置进度最大值
         */
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog =
                new ProgressDialog(Day9_Activity.this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("我是一个进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
        /* 模拟进度增加的过程
         * 新开一个线程，每个100ms，进度增加1
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress= 0;
                while (progress < MAX_PROGRESS){
                    try {
                        Thread.sleep(100);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                // 进度达到最大值后，窗口消失
                progressDialog.cancel();
            }
        }).start();
    }
    //文本dialog
    public void alertDialog_bt7(View view) {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(Day9_Activity.this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(Day9_Activity.this);
        inputDialog.setTitle("我是一个输入Dialog").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Day9_Activity.this,
                                editText.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
    //时间1
    public void timePickerDialog_bt8(View view) {
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(Day9_Activity.this, "日期" + hourOfDay+"时间"+minute, Toast.LENGTH_SHORT).show();
            }
        },0,0,true);
        timePickerDialog.show();
    }
    //时间4
    public void timePickerDialog_bt9(View view) {
        TimePickerDialog timePickerDialog=new TimePickerDialog(this,  AlertDialog.THEME_DEVICE_DEFAULT_DARK,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(Day9_Activity.this, "日期" + hourOfDay+"时间"+minute, Toast.LENGTH_SHORT).show();
            }
        },0,0,true);
        timePickerDialog.show();
    }
    //时间5
    public void timePickerDialog_bt10(View view) {
        TimePickerDialog timePickerDialog=new TimePickerDialog(this,  AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(Day9_Activity.this, "日期" + hourOfDay+"时间"+minute, Toast.LENGTH_SHORT).show();
            }
        },0,0,true);
        timePickerDialog.show();
    }

}

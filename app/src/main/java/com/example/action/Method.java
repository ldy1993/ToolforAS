package com.example.action;

import android.content.Context;
import android.content.Intent;

import com.example.function.View.Login.View_Login_Activity;
import com.example.function.View.Menu.View_Menu_Activity;
import com.example.function.algorithm.CalcPWD_Activity;
import com.example.function.algorithm.RSA_Activity;
import com.example.function.comm.FilesOrParamOrStringUpActivity;
import com.example.function.USB.USB_HOSTActivity;
import com.example.function.jni.jniTest.JniTestActivity;
import com.example.function.scan.idcard.one.IdCardMainActivity;
import com.example.function.study.day10.Day10_Activity;
import com.example.function.study.day4.A_activity;
import com.example.function.study.day5.Day5_Activity;
import com.example.function.study.day6.Day6_Activity;
import com.example.function.study.day7.Day7_ArrayAdapter_listView_Activity;
import com.example.function.study.day7.Day7_LeftSlide_listView_Activity;
import com.example.function.study.day8.Day8_Activity;
import com.example.function.study.day9.Day9_Activity;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2018/10/1
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class Method {
    private static final String TAG = "Method";

    private final static int SCAN=0;   //摄像头
    private final static int IDCARD=0; //识别银行卡
    private final static int JNI=1;   //JNI
    private final static int JNITEST=0; //JNI测试
    private final static int USB=3;   //USB
    private final static int USB_HOST=0; //USB测试
    private final static int COMM=4;   //通讯
    private final static int FILES_COMM=0; //文件上传
    private final static int STUDY=5;   //学习
    private final static int ACTIVITY_VALUE=0; //activity的传值
    private final static int VIEW_CREATE=1; //view的创建
    private final static int VIEW_CUSTOM=2; //自定义view
    private final static int VIEW_ListView=3; //ListView
    private final static int VIEW_LeftSlide_ListView =4; //左滑listview
    private final static int VIEW_PROGRESSBAR =5; //进度条
    private final static int VIEW_DIALOG =6; //对话框
    private final static int VIEW_POPUPWINDOW =7; //对话框
    private final static int JAVA_ALGORITHM=6; //java算法
    private final static int RSA=0; //RSA算法
    private final static int pwd=1; //保险箱计算密码
    private final static int VIEW=7; //好看界面
    private final static int LOGIN=0; //登录界面
    private final static int MENU=1; //菜单界面
    public static void implement_method(int location , int position, Context context)
    {
        Intent intent;
        switch (location)
        {

            case SCAN:
                switch (position)
                {
                    case IDCARD:
                         intent = new Intent(context, IdCardMainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                }
                break;
            case JNI:
                switch (position)
                {
                    case JNITEST:
                         intent = new Intent(context, JniTestActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;

                }
                break;
            case USB:
                switch (position)
                {
                    case USB_HOST:
                         intent = new Intent(context, USB_HOSTActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;

                }
                break;
            case COMM:
                switch (position)
                {
                    case FILES_COMM:
                         intent = new Intent(context, FilesOrParamOrStringUpActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                }
                break;
            case STUDY:
                switch (position)
                {
                    case ACTIVITY_VALUE:
                         intent = new Intent(context, A_activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;

                    case VIEW_CREATE:
                        intent = new Intent(context, Day5_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                    case VIEW_CUSTOM:
                        intent = new Intent(context, Day6_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                    case VIEW_ListView:
                        intent = new Intent(context, Day7_ArrayAdapter_listView_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                    case VIEW_LeftSlide_ListView:
                        intent = new Intent(context, Day7_LeftSlide_listView_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                    case VIEW_PROGRESSBAR:
                        intent = new Intent(context, Day8_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                    case VIEW_DIALOG:
                        intent = new Intent(context, Day9_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                    case VIEW_POPUPWINDOW:
                        intent = new Intent(context, Day10_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;

                }
                break;
            case JAVA_ALGORITHM:

                switch (position)
                {
                    case RSA:
                        intent = new Intent(context, RSA_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                    case pwd:
                         intent = new Intent(context, CalcPWD_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;

                }
                break;
            case VIEW:

                switch (position)
                {
                    case LOGIN:
                        intent = new Intent(context, View_Login_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                    case MENU:
                        intent = new Intent(context, View_Menu_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;
                }
                break;
        }
    }


}

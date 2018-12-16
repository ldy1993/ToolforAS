package com.example.multilistview;

import android.content.Context;
import android.content.Intent;

import com.example.USB.USB_HOSTActivity;
import com.example.jni.jniTest.JniTestActivity;
import com.example.scan.idcard.one.IdCardMainActivity;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2018/10/1
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class ImplementMethod {
    private final static int SCAN=0;   //摄像头
    private final static int IDCARD=0; //识别银行卡
    private final static int JNI=1;   //JNI
    private final static int JNITEST=0; //JNI测试
    private final static int USB=3;   //JNI
    private final static int USB_HOST=0; //JNI测试
    public static void implement_method(int location , int position, Context context)
    {
        switch (location)
        {
            case SCAN:
                switch (position)
                {
                    case IDCARD:
                        Intent intent = new Intent(context, IdCardMainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;

                }
                break;
            case JNI:
                switch (position)
                {
                    case JNITEST:
                        Intent intent = new Intent(context, JniTestActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;

                }
                break;
            case USB:
                switch (position)
                {
                    case USB_HOST:
                        Intent intent = new Intent(context, USB_HOSTActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                        context.startActivity(intent);
                        break;

                }
                break;

        }
    }

}

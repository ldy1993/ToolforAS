package com.example.action;

import android.content.Context;
import android.content.Intent;
import com.example.main.ConstantData;

/**
 * ================================================
 * @author ldy
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2018/10/1
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class Method {
    public static void implementMethod(int location, int position, Context context) {
        //获取点到的功能的名称
        String FunctionName=ConstantData.CHILD_NAMES[location][position];
      try {
          //通过点到功能的名称获取要启动的activity的名称
          String ActivityName=ConstantData.FUNCTION_MAP.get(FunctionName);
          //通过反射找到该activity的Class，然后显式启动
          startActivityClass(context,Class.forName(ActivityName));
      }catch (Exception e)
      {
          e.printStackTrace();
      }
    }

    /**
     * Activity的显示调用
     */
    private static void startActivityClass(Context context, Class cl) {
        Intent intent = new Intent(context, cl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}

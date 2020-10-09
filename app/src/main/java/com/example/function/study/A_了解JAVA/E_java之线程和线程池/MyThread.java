package com.example.function.study.A_了解JAVA.E_java之线程和线程池;

import com.ldy.log.Log;

public class MyThread extends Thread {
    private  boolean flag;
    private static final String TAG = "MyThread";
    public MyThread(boolean flag) {
        this.flag=flag;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            UtilTool.flag=flag;
            int result=UtilTool.addOrSub(1,1);
            boolean isSuccess;
            if(flag)
            {
                Log.e(TAG,this.getId()+"线程处理应该的结果为0."+"实际结果为"+result);
                isSuccess=(result==0);
            }else
            {
                Log.e(TAG,this.getId()+"线程处理应该的结果为2."+"实际结果为"+result);
                isSuccess=(result==2);
            }
            if(isSuccess)
            {
                Log.e(TAG,this.getId()+"处理成功"+i);
            }else
            {
                Log.e(TAG,this.getId()+"处理失败"+i);
            }
        }
    }
}

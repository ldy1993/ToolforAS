package com.example.function.study.C_业务代码.A_异步任务栈;

import android.app.Activity;
import android.widget.TextView;

import com.ldy.view.dialog.NetProcessDialog;
import com.ldy.log.Log;
import com.ldy.task.SingleAsyncTask;
import com.ldy.study.R;

/**
 * ================================================
 * @author 东阳
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/11/18
 * 描    述：
 * 修订历史：
 * ================================================

 */
public class SingleAsyncTaskTestAction extends SingleAsyncTask<String> {
    private  NetProcessDialog npd;
    private TextView tv;
    private Activity activity;
    public SingleAsyncTaskTestAction(Activity activity) {
        this.activity=activity;
         tv=activity.findViewById(R.id.tv);
         npd=new NetProcessDialog(activity,"子线程处理中");
    }

    @Override
    protected void predeal() {
        Log.e("ldy","主线程预处理");
        tv.setText(tv.getText()+"\n"+"主线程预处理");
        npd.show();
    }

    @Override
    protected <String> String doInBackgroud() {
        Log.e("ldy","子线程处理中");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String str= (String)("处理子线程的id："+Thread.currentThread().getId());
        return str;
    }

    @Override
    protected void endDeal(String s) {
        npd.dismiss();
        Log.e("ldy","主线程结束处理");
        Log.e("ldy","子线程返回什么呢："+s);
        tv.setText(tv.getText()+"\n"+s);
    }
}

package com.ldy.action;

import com.ldy.function.Log.Log;
import com.ldy.function.designMode.SingleAsyncTask;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/11/18
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class SingleAsyncTaskTestAction extends SingleAsyncTask<String> {
    @Override
    protected void predeal() {
        Log.e("ldy","主线程预处理");
    }

    @Override
    protected <String> String doInBackgroud() {
        Log.e("ldy","子线程处理中");
        return (String)"这是在子线程中处理后的结果1";
    }

    @Override
    protected void endDeal(String s) {
        Log.e("ldy","主线程结束处理");
        Log.e("ldy","子线程返回什么呢："+s);
    }
}

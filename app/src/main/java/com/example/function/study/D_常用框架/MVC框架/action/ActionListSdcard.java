package com.example.function.study.D_常用框架.MVC框架.action;

import com.example.function.study.D_常用框架.MVC框架.model.ActionResult;
import com.ldy.log.Log;

public class ActionListSdcard extends AAction {
    private static final String TAG = "ActionListSdcard";
    /**
     * 子类构造方法必须调用super设置ActionStartListener
     *
     * @param listener {@link ActionStartListener}
     */
    public ActionListSdcard(ActionStartListener listener) {
        super(listener);
    }

    @Override
    protected void process() {
        Log.e(TAG,"执行展示sdcard列表");
        //选中的文件名称
        String data="1.txt";
        setResult(new ActionResult(ActionResult.SUCCESS, data, "sdcard列表展示成功"));
    }
}

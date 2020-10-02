package com.example.function.study.G_了解框架.MVC框架.action;

import com.example.function.study.G_了解框架.MVC框架.model.ActionResult;
import com.ldy.log.Log;

public class ActionCommunication extends AAction {
    private static final String TAG = "ActionListSdcard";
    /**
     * 子类构造方法必须调用super设置ActionStartListener
     *
     * @param listener {@link ActionStartListener}
     */
    public ActionCommunication(ActionStartListener listener) {
        super(listener);
    }

    @Override
    protected void process() {
        Log.e(TAG,"上送网络数据");
        setResult(new ActionResult(ActionResult.SUCCESS, null, "网络通讯成功"));
    }
}

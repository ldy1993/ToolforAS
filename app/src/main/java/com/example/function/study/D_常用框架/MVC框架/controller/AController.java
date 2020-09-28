package com.example.function.study.D_常用框架.MVC框架.controller;


import com.example.function.study.D_常用框架.MVC框架.action.AAction;
import com.example.function.study.D_常用框架.MVC框架.model.ActionResult;
import com.example.function.study.D_常用框架.MVC框架.model.ControllerResult;
import com.ldy.log.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * 事物控制器
 * @author ldy
 */
public abstract class AController {
    private static final String TAG = "AController";
    /**
     * state和action的绑定关系表
     */
    private Map<String, AAction> actionMap;

    /**
     * Action结束监听器
     * @author ldy
     */
    private ControllerEndListener endListener;
    public interface ControllerEndListener {
        void onEnd(AController controller, ControllerResult result);
    }
    public void setEndListener(ControllerEndListener listener) {
        this.endListener = listener;
    }


    /**
     * 单个state绑定action
     * @param state
     * @param action
     */
    protected void bind(final String state, AAction action) {
        if (actionMap == null) {
            actionMap = new HashMap<String, AAction>();
        }
        actionMap.put(state, action);
        action.setEndListener(new AAction.ActionEndListener() {
            @Override
            public void onEnd(AAction action, ActionResult result) {
                onActionResult(state, result);
            }
        });
    }

    /**
     * 执行state状态绑定的action
     *
     * @param state
     */
    public void gotoState(String state) {
        AAction action = actionMap.get(state);
        if (action != null) {
            action.execute();
        } else {
            Log.e(TAG, "无效State:" + state);
        }
    }

    /**
     * 执行交易
     */
    public void execute() {
        bindStateOnAction();
        onStart();
    }

    /**
     * state绑定action抽象方法，在此实现中调用{@link #bind(String, AAction)}方法， 并在最后调用{@link #gotoState(String)}方法，执行第一个state
     */
    protected abstract void bindStateOnAction();
    /**
     * 调用前初始化
     */
    protected abstract void onStart();
    /**
     * action结果处理
     *
     * @param currentState ：当前State
     * @param result       ：当前Action执行的结果
     */
    protected abstract void onActionResult(String currentState, ActionResult result);

}

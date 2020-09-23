package com.example.function.study.D_手写框架.MVC框架.action;


import com.example.function.study.D_手写框架.MVC框架.model.ActionResult;

/**
 * Action 抽象类定义
 * 
 * @author ldy
 * 
 */
public abstract class AAction {
    /**
     * 子类构造方法必须调用super设置ActionStartListener
     * @param listener {@link ActionStartListener}
     */
    public AAction(ActionStartListener listener) {
        this.startListener = listener;
    }

    /**
     * Action结束监听器
     * @author ldy
     */
    private ActionStartListener startListener;
    public interface ActionStartListener {
        void onStart(AAction Action);
    }
    /**
     * Action结束监听器
     * @author ldy
     */
    private ActionEndListener endListener;
    public interface ActionEndListener {
        void onEnd(AAction Action, ActionResult result);
    }
    public void setEndListener(ActionEndListener listener) {
        this.endListener = listener;
    }


    /**
     * Action的具体处理方法
     */
    protected abstract void process();

    /**
     * 执行Action之前需要先设置{@link ActionEndListener},
     * 此接口内部先调用{@link ActionStartListener#onStart(AAction)} ,
     * 再调用{@link AAction#process}
     */
    public void execute() {
        if (startListener != null) {
            startListener.onStart(this);
        }
        process();
    }


    /**
     * 设置Action结果, 次接口内部调用{@link ActionEndListener#onEnd(AAction, ActionResult)}方法
     * @param result {@link ActionResult}
     */
    public void setResult(ActionResult result) {
        if (endListener != null) {
            endListener.onEnd(this, result);
        }
    }
}
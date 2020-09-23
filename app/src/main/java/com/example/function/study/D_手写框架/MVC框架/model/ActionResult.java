package com.example.function.study.D_手写框架.MVC框架.model;

public class ActionResult {
    public final static int SUCCESS=0;
    /**
     * 返回结果（为 -1000 时显示detailMessage）
     */
    int ret;
    /**
     * 返回数据
     */
    Object data;
    /** 返回结果详细信息(一般为服务器响应后抛出的异常信息，显示时以服务器给的信息为准) **/
    String detailMessage;
    public ActionResult(int ret, Object data, String message) {
        this.ret = ret;
        this.data = data;
        this.detailMessage = message;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}

package com.example.function.study.D_手写框架.MVC框架.controller;

import com.example.function.study.D_手写框架.MVC框架.action.AAction;
import com.example.function.study.D_手写框架.MVC框架.action.ActionCommunication;
import com.example.function.study.D_手写框架.MVC框架.action.ActionListSdcard;
import com.example.function.study.D_手写框架.MVC框架.model.ActionResult;
import com.ldy.log.Log;

public class UpFileController extends AController {
    private static final String TAG = "UpFileController";

    enum State {
        选择文件,
        网络通讯;
    }

    @Override
    protected void bindStateOnAction() {
        ActionListSdcard actionListSdcard = new ActionListSdcard(new AAction.ActionStartListener() {
            @Override
            public void onStart(AAction Action) {

            }
        });
        bind(State.选择文件.toString(), actionListSdcard);
        ActionCommunication actionCommunication = new ActionCommunication(new AAction.ActionStartListener() {
            @Override
            public void onStart(AAction Action) {

            }
        });
        bind(State.网络通讯.toString(), actionCommunication);
    }

    @Override
    protected void onStart() {
        gotoState(State.选择文件.toString());
    }

    @Override
    protected void onActionResult(String currentState, ActionResult result) {

        if (result.getRet() != ActionResult.SUCCESS) {
            return;
        }
        State state = State.valueOf(currentState);
        switch (state) {
            case 选择文件:
                Log.e(TAG,"获取到的文件名称"+result.getDetailMessage());
                gotoState(State.网络通讯.toString());
                break;
            case 网络通讯:
                Log.e(TAG,"网络通讯"+result.getDetailMessage());
                break;
        }
    }
}

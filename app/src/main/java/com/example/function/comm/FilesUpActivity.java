package com.example.function.comm;

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.example.View.R;
import com.github.ldy.httpserver.comm.CommManager;
import com.github.ldy.window.NetProcessWindow;

import me.jerry.framework.comm.CommEntity;
import me.jerry.framework.comm.ICommEventListener;
import me.jerry.framework.comm.NetException;

public class FilesUpActivity extends Activity {
    private static final String TAG = "FilesUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_up);
        CommManager.init();
        CommManager.commVerification("0820202020", this, new ICommEventListener() {
            @Override
            public void onStart(CommEntity commEntity) {
                Log.e(TAG, "onStart: 开始通讯");
                NetProcessWindow.getInstance(FilesUpActivity.this).show();
            }

            @Override
            public void onEnd(CommEntity commEntity) {
                Log.e(TAG, "onEnd: "+commEntity.getResponseBean().body );
                NetProcessWindow.getInstance(FilesUpActivity.this).dismiss();
            }

            @Override
            public void onError(NetException e, CommEntity commEntity) {
                Log.e(TAG, "onError: 通讯失败" );
                NetProcessWindow.getInstance(FilesUpActivity.this).dismiss();
            }
        });
    }

}

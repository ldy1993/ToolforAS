package com.example.function.comm;

import android.os.Bundle;
import android.app.Activity;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ldy.View.dialog.NetProcessDialog;
import com.ldy.action.AuthorizationAction;
import com.ldy.function.Network.service.NetComplateListener;
import com.ldy.study.R;

public class FilesOrParamOrStringUpActivity extends Activity {
    private static final String TAG = "FilesOrParamOrStringUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_param_string_up);
        Button bt=findViewById(R.id.bt);
        Button bt_new=findViewById(R.id.bt_new);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetProcessDialog.getInstance(FilesOrParamOrStringUpActivity.this).show();
                AuthorizationAction.commVerification("0821125962",listener);
//                CommManager.commVerification("0820202020", 300000,300000, listener);
            }
        });

        bt_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetProcessDialog.getInstance(FilesOrParamOrStringUpActivity.this).show();
//                CommManager.commVerificationFromJerry("0820202020");
                AuthorizationAction.commVerification("0820641178",  listener);
            }
        });
        }

    NetComplateListener listener=  new NetComplateListener() {

        @Override
        public void onNetComplate(String data) {
            //在子线程中弹出弹框
            NetProcessDialog.getInstance(FilesOrParamOrStringUpActivity.this).dismiss();
            Looper.prepare();
            Toast.makeText(FilesOrParamOrStringUpActivity.this,"解锁成功",Toast.LENGTH_SHORT).show();
            Looper.loop();


        }

        @Override
        public void onNetError(int errorCode, String errorMessage) {
            NetProcessDialog.getInstance(FilesOrParamOrStringUpActivity.this).dismiss();
            Log.e("ldy","通讯失败");
            //在子线程中弹出弹框
            Looper.prepare();
            Toast.makeText(FilesOrParamOrStringUpActivity.this,""+errorCode+":"+errorMessage,Toast.LENGTH_SHORT).show();
            Looper.loop();

        }

        @Override
        public void onWorkFlowError(String errorCode, String errorMessage) {
            NetProcessDialog.getInstance(FilesOrParamOrStringUpActivity.this).dismiss();
            Log.e("ldy","通讯失败"+errorCode+":"+errorMessage);
            //在子线程中弹出弹框
            Looper.prepare();
            Toast.makeText(FilesOrParamOrStringUpActivity.this,"通讯失败"+errorCode+":"+errorMessage,Toast.LENGTH_SHORT).show();
            Looper.loop();
        }

        @Override
        public void onPackError(int errorCode, String errorMessage) {
            NetProcessDialog.getInstance(FilesOrParamOrStringUpActivity.this).dismiss();
            Log.e("ldy","通讯失败");
            //在子线程中弹出弹框
            Looper.prepare();
            Toast.makeText(FilesOrParamOrStringUpActivity.this,"通讯失败"+errorCode+":"+errorMessage,Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    };
}

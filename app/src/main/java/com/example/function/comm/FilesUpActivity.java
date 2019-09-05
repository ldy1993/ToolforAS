package com.example.function.comm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ldy.View.dialog.NetProcessDialog;
import com.ldy.function.Network.CommManager;
import com.ldy.study.R;
import com.ldy.function.Network.CommManager.NetComplateListener;

public class FilesUpActivity extends Activity {
    private static final String TAG = "FilesUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_up);
        Button bt=findViewById(R.id.bt);
        Button bt_new=findViewById(R.id.bt_new);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetProcessDialog.getInstance(FilesUpActivity.this).show();
                CommManager.init(FilesUpActivity.this);
                CommManager.commVerificationFromJerry("0821125962");
//                CommManager.commVerification("0820202020", 300000,300000, listener);
            }
        });
        bt_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetProcessDialog.getInstance(FilesUpActivity.this).show();
                CommManager.init(FilesUpActivity.this);
//                CommManager.commVerificationFromJerry("0820202020");
                CommManager.commVerification("0820641178", 300000,300000, listener);
            }
        });
        }

    NetComplateListener<String> listener=  new NetComplateListener() {

        @Override
        public void onNetComplate(Object data) {
            //在子线程中弹出弹框
            NetProcessDialog.getInstance(FilesUpActivity.this).dismiss();
            Looper.prepare();
            Toast.makeText(FilesUpActivity.this,"解锁成功",Toast.LENGTH_SHORT).show();
            Looper.loop();


        }

        @Override
        public void onNetError(int errorCode, String errorMessage) {
            NetProcessDialog.getInstance(FilesUpActivity.this).dismiss();
            Log.e("ldy","通讯失败");
            //在子线程中弹出弹框
            Looper.prepare();
            Toast.makeText(FilesUpActivity.this,""+errorCode+":"+errorMessage,Toast.LENGTH_SHORT).show();
            Looper.loop();

        }

        @Override
        public void onWorkFlowError(String errorCode, String errorMessage) {
            NetProcessDialog.getInstance(FilesUpActivity.this).dismiss();
            Log.e("ldy","通讯失败"+errorCode+":"+errorMessage);
            //在子线程中弹出弹框
            Looper.prepare();
            Toast.makeText(FilesUpActivity.this,"通讯失败"+errorCode+":"+errorMessage,Toast.LENGTH_SHORT).show();
            Looper.loop();
        }

        @Override
        public void onPackError(int errorCode, String errorMessage) {
            NetProcessDialog.getInstance(FilesUpActivity.this).dismiss();
            Log.e("ldy","通讯失败");
            //在子线程中弹出弹框
            Looper.prepare();
            Toast.makeText(FilesUpActivity.this,"通讯失败"+errorCode+":"+errorMessage,Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    };
}

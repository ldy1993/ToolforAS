package com.example.function.comm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ldy.View.dialog.NetProcessDialog;
import com.ldy.action.CGBAggregatePayAction;
import com.ldy.function.Network.Instantiation.httpPost.CGBAggregateCommImpl;
import com.ldy.function.Network.service.NetComplateListener;
import com.ldy.study.R;

public class PayActivity extends Activity {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(PayActivity.this, (String)msg.obj, Toast.LENGTH_SHORT).show();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Button bt=findViewById(R.id.bt_conn_pay_handshake);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CGBAggregatePayAction.handshake(PayActivity.this,new NetComplateListener() {
                    @Override
                    public void onNetComplate(String data) {
                        NetProcessDialog.getInstance(PayActivity.this).dismiss();
                    Message message = new Message();
                        message.obj="data=" + data;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onNetError(int errorCode, String errorMessage) {
                        NetProcessDialog.getInstance(PayActivity.this).dismiss();
                        Message message = new Message();
                        message.obj="onNetError" ;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onWorkFlowError(String errorCode, String errorMessage) {
                        NetProcessDialog.getInstance(PayActivity.this).dismiss();
                        Message message = new Message();
                        message.obj="onWorkFlowError" ;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onPackError(int errorCode, String errorMessage) {
                        NetProcessDialog.getInstance(PayActivity.this).dismiss();
                        Message message = new Message();
                        message.obj="onPackError" ;
                        handler.sendMessage(message);
                    }
                });
            }
        });
    }

}

package com.example.function.comm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ldy.action.CGBAggregatePayAction;
import com.ldy.function.Network.Instantiation.httpPost.CGBAggregateCommImpl;
import com.ldy.study.R;

public class PayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Button bt=findViewById(R.id.bt_conn_pay_handshake);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CGBAggregatePayAction.handshake(PayActivity.this);
            }
        });
    }

}

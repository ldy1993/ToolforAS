package com.example.USB;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.multilistview.R;
import com.ldy.library.QR55.USB_HOST;

public class USB_HOSTActivity extends Activity {
    private TextView tvResult;
    private TextView Bt1;
    private TextView Bt2;
    private TextView Bt3;
    private TextView Bt4;
    private TextView Et1;
    private TextView Et2;
    private TextView Et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb__host);
        tvResult = (TextView) findViewById(R.id.tvResult);
        Bt1 = (TextView) findViewById(R.id.Bt1);
        Bt2 = (TextView) findViewById(R.id.Bt2);
        Bt3 = (TextView) findViewById(R.id.Bt3);
        Bt4 = (TextView) findViewById(R.id.Bt4);
        Et1 = (TextView) findViewById(R.id.Et1);
        Et2 = (TextView) findViewById(R.id.Et2);
        Et3 = (TextView) findViewById(R.id.Et3);
        USB_HOST.init(USB_HOSTActivity.this,listener);
        Bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USB_HOST.doSale(Et1.getText().toString());
            }
        });
        Bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USB_HOST.doSaleResult("99999999",Et2.getText().toString(),Et3.getText().toString());
            }
        });
        Bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Et1.setText("2000");
                Et2.setText("1000");
                Et3.setText("5505616");
            }
        });
        Bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             USB_HOST.cancel();
            }
        });
    }
    USB_HOST.Listener listener=new USB_HOST.Listener() {
        @Override
        public void onRecvOk(String result) {
            updateResult("卡号为:"+result);
//            updateResult("2秒后自动发起结果确认");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            USB_HOST.cancel();
//
            updateResult("发起结果确认");
            USB_HOST.doSaleResult(result,Et2.getText().toString(),Et3.getText().toString());
        }

        @Override
        public void onRecvErr(int errcode) {
            updateResult("返回错误码"+errcode);
        }

        @Override
        public void onRecvTimeout() {
            updateResult("没有收到数据");
        }
    };
    private void updateResult(String result) {
        String s = tvResult.getText().toString();
        if(s!=null) {
            tvResult.setText(s + "\n" + result);
        }
        else
        {
            tvResult.setText(result);
        }
    }
}

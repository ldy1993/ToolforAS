package com.example.function.algorithm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ldy.sign.service.SignServiceImpl;
import com.ldy.study.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RSA_Activity extends Activity {
    private static final String TAG = "RSA_Activity";
    private TextView et_data;
    private TextView tv_data;
    private TextView et_sign;
  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SignServiceImpl signServiceImpl=new SignServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
        et_data = findViewById(R.id.et_data);
        et_sign = findViewById(R.id.et_sign);
        tv_data = findViewById(R.id.tv_data);
        Button bt_encrypt = findViewById(R.id.bt_encrypt);
        Button bt_decrypt = findViewById(R.id.bt_decrypt);
        bt_encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("time", simpleDateFormat.format(new Date()));
                    jsonObject.put("sn", "0820641181");
                    String preStr = jsonObject.toString();
                    et_data.setText(preStr);
                    String sign = signServiceImpl.getSign("RSA_1_256", preStr);
                    et_sign.setText(sign);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        bt_decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String preStr = et_data.getText().toString();
                    String sign = et_sign.getText().toString();
                    if (sign == null || sign.isEmpty()) {
                        Toast.makeText(RSA_Activity.this, "请先加密", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean b = signServiceImpl.verifySign(preStr, sign, "RSA_1_256");
                        if (b) {
                            tv_data.setText("验签通过");
                        } else {
                            tv_data.setText("验签不通过");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    tv_data.setText("解密数据异常抛出");
                }
                et_sign.setText("");
            }
        });

    }
}

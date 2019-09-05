package com.example.function.algorithm;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ldy.function.sign.SignUtil;
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
    private final String mchPrivateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDVBYmxp19QCAStfySQat3HRyKEAyQrv1HFeubvwpCfFr3/gH2qhXtzpNWFJ6YeSUtI3wnGd3JpSKBMgVcg2vR4JYVP58G0vV/4KnghEocB0hOGiehrXnJlmU0ex5tSIU36IU9VfvqcFebBXfALPKQXb5YX37Ngs92HdNpYckR3ILH5zQkj3YyQGBa+IS4yDIUohi2OVcdDIE8LrBDeXVMnuoQ8Z9CNr0ea5VCj33Gv2qAhJCQUkUwZaOldFhiTLA8e66ln//cPyIauv9VzMVTnKkJAkIi7EP2rJONPuDP35YiB1oSIiASCLoxm/xtXzv+WRk02WKVCzFOXnVQfgNDXAgMBAAECggEBAMZg6eIv5Gs7jF+mT/7OBstWS6Z05nJ3MUefNQGDR/tfyoDuwBjVRipkDxR54BHEX//09b2F715s7BtTODi4r4JULtpDEfmngoAxzL2/60qewOBB/dg/lHlZUqCfct4BdgTCkIcmQWyqWMMrICjWyFmq6TRCd2JPHrbO86f6ghgM/4l1yzK6pSXshsIEn3vwTfgpx6mxQoiRDN7d4CHpRmcIpAy0AyLOZ1qMxxaTZCwAYoLiUYIfETFgRa0p3Ja9FYcllYb9tpEZvWFhxatkE0VUCBHuxPqOikwc6AzSBn2ehvDbRvzHDYYnLZkAf/a1UKWWZPdh6CBtPE4YLQlmUYECgYEA8NR6q+/8TGWvpffft/ta6bl9uPN08+qUaX/odOLRdPfoLhN+TjrMgAOumfgVqjldA80lvDPd84obSgX/dQHNunlp8m8QQ5kmy/X+XmM4SXTpE1wsfHo4oqXk9IrAV+OkDr3SLVZu+CBGNVBSVN0/UhcYzgL/iQgiBiwkfmkJP98CgYEA4nCiClmsVUUlLKDCYNYE8+LE8PMX8MzwEpQkQVkpg3MbEspIkwopxoNMLsEnMCCIwe5MXzOdjdhZTc84sOux1YSbzUSgHbULgToVse6LJ2SId6aEoq4W2a8UseQqrvKT8AUseXTQ02px1m74GtefuGPvI4jSA+QijEGtAGO8rgkCgYEAqE1VjWMhhqJppiscBY97cWtYGTDKwstk/HWPFmCM0JmPgOPOmQ0ubSgllSRyv4tnHYj4nhYBPnl9qLWYWUTxaSgAipRjYVpeapt3MK9WXr99QzJfRH6FpeUZpGxzDvjfZg3yHA0TcAPeDNQdNYGJBu1tQEuIGVO9s2KV/mGZPTECgYEAiCLhOMnK0wUcIWWRFzj0h0/5yUbNSOddGj5YQbWCQNYuG7DjywscLI35ek6D6epVbgp/fekBOyssHZyM38kEBHOSW+l7PgD/prSuIGhi3s8RQKYrLcbuU/dvwLeFdeK/UFk78NVrSv++6wWSQkkKbIggWMPER7SblKlTgl10LUkCgYAEK5g5tSh72KUG7av8kLbPpX1kzxVjeKlCtqToqZxEZE6m098suGcp2/69EjVA8PiEJe6WJFWchSC1vY9PPK6SieRLfj1C7EUhLz5zgSA1aw9O86AHw9sBa0BL2GgZwi/CClG3rgVumPI6XFu52jowBJUocr6+neifKuwIrGHmww==";
    private final String platPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1QWJsadfUAgErX8kkGrdx0cihAMkK79RxXrm78KQnxa9/4B9qoV7c6TVhSemHklLSN8JxndyaUigTIFXINr0eCWFT+fBtL1f+Cp4IRKHAdIThonoa15yZZlNHsebUiFN+iFPVX76nBXmwV3wCzykF2+WF9+zYLPdh3TaWHJEdyCx+c0JI92MkBgWviEuMgyFKIYtjlXHQyBPC6wQ3l1TJ7qEPGfQja9HmuVQo99xr9qgISQkFJFMGWjpXRYYkywPHuupZ//3D8iGrr/VczFU5ypCQJCIuxD9qyTjT7gz9+WIgdaEiIgEgi6MZv8bV87/lkZNNlilQsxTl51UH4DQ1wIDAQAB";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
        et_data = findViewById(R.id.et_data);
        et_sign = findViewById(R.id.et_sign);
        tv_data = findViewById(R.id.tv_data);
        Button bt_encrypt = findViewById(R.id.bt_encrypt);
        Button bt_decrypt = findViewById(R.id.bt_decrypt);
        ((TextView) findViewById(R.id.tv_mchPrivateKey)).setText(mchPrivateKey);
        ((TextView) findViewById(R.id.tv_platPublicKey)).setText(platPublicKey);

        bt_encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("time", simpleDateFormat.format(new Date()));
                    jsonObject.put("sn", "0820641181");
                    String preStr = jsonObject.toString();
                    et_data.setText(preStr);
                    String sign = SignUtil.getSign("RSA_1_256", preStr, mchPrivateKey);
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
                    Log.e(TAG, "preStr: " + preStr);
                    Log.e(TAG, "sign: " + sign);
                    Log.e(TAG, "mchPrivateKey: " + mchPrivateKey);
                    if (sign == null || sign.isEmpty()) {
                        Toast.makeText(RSA_Activity.this, "请先加密", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean b = SignUtil.verifySign(preStr, sign, "RSA_1_256", platPublicKey);
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

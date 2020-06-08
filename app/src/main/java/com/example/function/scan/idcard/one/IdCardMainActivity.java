package com.example.function.scan.idcard.one;

import android.app.Activity;
import android.util.Log;
import android.view.View;

        import android.content.Intent;
        import android.os.Bundle;
import android.widget.EditText;
        import android.widget.ImageView;

import com.ldy.study.R;
import com.example.function.scan.idcard.one.smartvision_bankCard.ScanCamera;

//import io.card.payment.CardIOActivity;
//        import io.card.payment.CreditCard;

public class IdCardMainActivity extends Activity implements View.OnClickListener{

    private EditText et_name;//显示银行卡姓名
    private EditText et_cardno;//显示银行卡的卡号
    private int MY_SCAN_REQUEST_CODE = 100;//请求码
    private ImageView img;//开启银行卡扫描

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_a_scan_idcard_activity_main);
        //显示返回按钮
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //初始化控件
        et_name = (EditText) findViewById(R.id.et_name);
        et_cardno=findViewById(R.id.et_cardno);
        img = (ImageView) findViewById(R.id.scanButton);
        img.setOnClickListener(this);//点击事件

    }

    @Override
    protected void onResume() {

        super.onResume();
//        et_cardno.requestFocus();
    }
//    /**
//     * 按钮的点击事件
//     */
//    public void onScanPress() {
//        //创建意图
//        Intent scanIntent = new Intent(this, CardIOActivity.class);
//        //关闭键盘显示
//        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true);
//        //跳转界面
//        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
//    }

    /**
     * 返回界面
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String resultStr;//用来保存获取的结果
        String PicR = data.getExtras().getString("PicR");
        char[] StringR =data.getCharArrayExtra("StringR");
        Log.i("ldy", PicR+"\\\\\\"+String.valueOf(StringR));
//        if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
//            //获取返回内容
//            CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
//            //获取银行卡卡号
//            resultStr = scanResult.getFormattedCardNumber();
//        } else
            {
            resultStr = "扫描已取消";
        }
        et_cardno.setText(String.valueOf(StringR));
    }

    @Override
    public void onClick(View v) {
//        onScanPress();
        Intent intentTack = new Intent(this, ScanCamera.class);
        startActivityForResult(intentTack,200);
    }
}


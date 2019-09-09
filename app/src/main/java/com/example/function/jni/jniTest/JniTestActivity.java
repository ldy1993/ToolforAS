package com.example.function.jni.jniTest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.KeyTool.KEY;
import com.ldy.study.R;
import com.ldy.View.CustomWidget.codeinput.CodeInput;

public class JniTestActivity extends Activity {
    private static final String TAG = "JniTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jnitestactivity);

        TextView tv=findViewById(R.id.tv);
        tv.setText("");
        LinearLayout ll=findViewById(R.id.ll);
        LinearLayout ll_error=findViewById(R.id.ll_error);
        try {

        //此处应该抛异常，保护了RSA私钥。
     Toast.makeText(JniTestActivity.this, KEY.getMchPrivateKey(), Toast.LENGTH_SHORT).show();

        }catch (Exception ee)
        {
            ee.printStackTrace();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            Toast.makeText(JniTestActivity.this, "Throwable异常了", Toast.LENGTH_LONG).show();
            tv.setText("调用jni抛出异常"+"\n"+e.getMessage());
            ll.setVisibility(View.GONE);
            ll_error.setVisibility(View.VISIBLE);
        }
//        try {
//            CodeInput codeInput= (CodeInput) findViewById(R.id.inputdata);
//            codeInput.setCodeReadyListener(new CodeInput.codeReadyListener() {
//                @Override
//                public void onCodeReady(Character[] code) {
//                    int[] data=new int[code.length];
//                    int i=0;
//                    for(Character character:code)
//                    {
//                        data[i]= Integer.parseInt(character.toString());
//                        Log.e(TAG, "onCodeReady1: "+ data[i]);
//                        i++;
//
//                    }
//                    data=jniTestClass.heapsort(data);
//                    String temp = "";
//                    for(int temp2:data)
//                    {
//                        temp=temp+" "+temp2;
//
//                    }
//                    Log.e(TAG, "onCodeReady2: "+temp);
//                    TextView tv_heapsort= (TextView) findViewById(R.id.tv_heapsort);
//                    tv_heapsort.setText(temp);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

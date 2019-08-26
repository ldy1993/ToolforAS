package com.example.function.scan.idcard.one;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.View.R;

/**
 * Created by wanglu on 2017/2/7.
 */

public class ShowResult extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_a_activity_result);

        TextView result = (TextView) findViewById(R.id.number);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        int[] picR = getIntent().getIntArrayExtra("PicR");
        char[] StringR = getIntent().getCharArrayExtra("StringR");
        result.setText(String.valueOf(StringR));
        Bitmap bitmap = Bitmap.createBitmap(picR, 400, 80, Bitmap.Config.ARGB_8888);
        imageView.setImageBitmap(bitmap);
    }
}

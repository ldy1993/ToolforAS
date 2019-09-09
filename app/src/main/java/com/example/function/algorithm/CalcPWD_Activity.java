package com.example.function.algorithm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ldy.function.sign.service.SignServiceImpl;
import com.ldy.study.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class CalcPWD_Activity extends Activity {
    private static final String TAG = "CalcPWD_Activity";
    private TextView et_data;
    private TextView tv_data;
    private TextView et_sign;
    private  int[] sum={0,1,2,3,5,6,7,8,9};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
//        int [] a=delete(sum,5);
//        for(int i=0;i<a.length;i++)
//        {
//            Log.e("ldy","删除了5元素"+a[i]);
//        }
        calc();

    }
    private void calc() {
        int G,I,V,R,E,M,O,Y,N;
        for (int g=0;g<9;g++)
        {
            G=sum[g];
            int[] sumI =delete(sum,g);
            for (int i=0;i<8;i++)
            {
                I=sumI[i];
                int[] sumV =delete(sumI,i);
                for (int v=0;v<7;v++)
                {
                    V=sumV[v];
                    int[] sumR =delete(sumV,v);
                    for (int r=0;r<6;r++)
                    {
                        R=sumR[r];
                        int[] sumE =delete(sumR,r);
                        for (int e=0;e<5;e++)
                        {
                            E=sumE[e];
                            int[] sumM =delete(sumE,e);
                            for (int m=0;m<4;m++)
                            {
                                M=sumM[m];
                                int[] sumO =delete(sumM,m);
                                for (int o=0;o<3;o++)
                                {
                                    O=sumO[o];
                                    int[] sumY =delete(sumO,o);
                                    for (int y=0;y<2;y++)
                                    {
                                        Y=sumY[y];
                                        int[] sumN =delete(sumY,y);
                                        for (int n=0;n<1;n++)
                                        {
                                            N=sumN[n];
                                            int temp=1000*G+100*I+10*V+10*R-8*E-9000*M-900*O-Y-100*N;
                                            if(temp==0)
                                            {
                                                Log.e("ldy","密码为"+N*1000000+O*100000+M*10000+O*1000+N*100+E*10+Y);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public int[] delete(int[] a, int index) {
        int[] b = new int[a.length - 1];
        System.arraycopy(a, 0, b, 0, index);
        if(index < a.length - 1)
            System.arraycopy(a, index + 1, b, index, a.length - index - 1);
        return b;
    }
}

package com.example.function.study.day8;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import com.ldy.Utils.UnitUtils;
import com.ldy.function.Log.Log;
import com.ldy.study.R;

public class Day8_Activity extends Activity {
    private ProgressBar pb;
    int i = 0;
    private final int progressBar=0;
// volatile是一个类型修饰符（type specifier），被设计用来修饰被不同线程访问和修改的变量
    public volatile boolean exit = false;
    private Thread thread;
private Handler handler=new Handler(){
    public void handleMessage(Message msg) {
        switch (msg.what)
        {
            case progressBar:
                pb.setProgress(msg.arg1);
                break;
        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day8);
         pb=findViewById(R.id.pb);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {


                while (!exit) {
                    try {
                        thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = new Message();
                    if (i <= 100) {
                        i++;
                    } else {
                        i = 0;
                    }
                    message.what = progressBar;
                    message.arg1 = i;
                    handler.sendMessage(message);

                }
            }
        });
        thread.start();
//                setProgress(int) 设置第一进度
//        setSecondaryProgress(int) 设置第二进度
    }
public void begin(View view)
{
    startThread();
}
    public void stop(View view)
    {
        exit=true;
    }
    private void startThread()
    {

        if(exit==true) {
            exit = false;
            thread = new Thread(new Runnable() {
                @Override
                public void run() {


                    while (!exit) {
                        try {
                            thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                            Message message = new Message();
                            if (i <= 100) {
                                i++;
                            } else {
                                i = 0;
                            }
                            message.what = progressBar;
                            message.arg1 = i;
                            handler.sendMessage(message);

                    }
                }
            });
            thread.start();
        }

    }
}

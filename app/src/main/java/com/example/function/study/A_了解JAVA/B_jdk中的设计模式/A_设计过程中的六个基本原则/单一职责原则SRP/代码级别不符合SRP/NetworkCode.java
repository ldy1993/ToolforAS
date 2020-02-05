package com.example.function.study.A_了解JAVA.B_jdk中的设计模式.A_设计过程中的六个基本原则.单一职责原则SRP.代码级别不符合SRP;

import com.ldy.function.Log.Log;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/3
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class NetworkCode {
    public final static String GET_PARAM="GET_PARAM";
    public final static String POST_PARAM="POST_PARAM";
    public final static String POST_FILE="POST_FILE";
    public void execute(String type)
    {
        switch (type)
        {
            case GET_PARAM:
                getParam();
                break;
            case POST_PARAM:
                postParam();
                break;
            case POST_FILE:
                postFile();
                break;
                default:
                    break;
        }
    }
    private void getParam()
    {
        Log.e("ldy","以get方式上送param");
    }
    private void postParam(){
        Log.e("ldy","以post方式上送param");
    }
    private void postFile(){
        Log.e("ldy","以post方式上送file");
    }
}

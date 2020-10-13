package com.example.function.study.A_了解JAVA.H_java之注解.annotation.xml;

import android.util.Log;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/13
 * 描    述：完成以下xml的组包
 * <LinearLayout >
 *     <TextView>haha</TextView>
 * 	  <Button>hehe</Button>
 * </LinearLayout>
 * 修订历史：
 * ================================================
 */
class testUtil {
    private static final String TAG = "testUtil";
    public void testPackXml()
    {
        packXml linearLayout=new packXml();
        linearLayout.setArg1("hehe");
        linearLayout.setArg2("haha");
        Log.d(TAG, "testPackXml: "+linearLayout.toXml());
    }
}

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
public class TestUtil {
    private static final String TAG = "testUtil";
    public static void testPackXml()
    {
        PackXml packXml=new PackXml();
        PackBodyXml packBodyXml=new PackBodyXml();
        packBodyXml.setArg("good");
        packXml.setArg1(packBodyXml);
        packXml.setArg2("haha");
        Log.d(TAG, "testPackXml: "+XmlCompiler.toXml("LinearLayout",packXml));
    }
}

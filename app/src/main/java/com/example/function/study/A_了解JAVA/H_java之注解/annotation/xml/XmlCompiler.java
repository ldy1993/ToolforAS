package com.example.function.study.A_了解JAVA.H_java之注解.annotation.xml;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.xml.XStreamAlias;
/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/14
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class XmlCompiler {
    private static final String TAG = "XmlCompiler";

    public static String toXml(String root,Object object) {
        StringBuffer stringBuffer=new StringBuffer();
        try {
            setXml(root, stringBuffer, true);
            dealAnnotation(object,stringBuffer);
            setXml(root, stringBuffer, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }



    private static void dealAnnotation(Object object, StringBuffer stringBuffer) throws IllegalAccessException {
        Class clazz=object.getClass();
        if (clazz.isAnnotationPresent(XStreamRoot.class)) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                String sonTag=declaredField.getAnnotation(XStreamAlias.class).value();
                setXml(sonTag, stringBuffer, true);
                //如果成员或者方法是私有变量，需要把setAccessible设置为true；
                declaredField.setAccessible(true);
                Object obj= declaredField.get(object);
                dealAnnotation(obj,stringBuffer);
                setXml(sonTag, stringBuffer, false);
            }
        }else
        {
            stringBuffer.append(object.toString());
        }
    }
    private static void setXml(String root, StringBuffer stringBuffer, boolean isBegin) {
        if(isBegin)
        {
            stringBuffer.append("<"+ root + ">");
        }else
        {
            stringBuffer.append("</"+ root + ">");
        }

    }
}

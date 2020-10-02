package com.example.function.study.A_了解JAVA.H_java之注解.annotation;

import android.app.Activity;
import android.view.View;

import com.ldy.log.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Compiler {
    public static void onBindCompiler(Object object) {
        OnBind value = null;
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(OnBind.class)) {
                value = field.getAnnotation(OnBind.class);
                Log.e("ldy", value.value());
            }

        }
    }

    public static void findViewByIdCompiler(Activity activity) {
        Log.e("ldy","findViewByIdCompiler");
        FindViewById value = null;
        Class clazz = null;
        try {
            clazz = activity.getClass();
            if (clazz != null) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(FindViewById.class)) {
                        value = field.getAnnotation(FindViewById.class);
                        Log.e("ldy","value="+value.value());
                        //判断该属性是否是属于View的子类类型，并且不是静态属性
                        if(View.class.isAssignableFrom(field.getType()) && !Modifier.isStatic(field.getModifiers())) {
                            Method method =clazz.getMethod("findViewById", int.class);
                            method.setAccessible(true);
                            //将指定对象变量上此 Field 对象表示的字段设置为指定的新值。
                            Object view = method.invoke(activity, value.value());
                            try {
                                // 通过反射拿私有对象，需要加上这一句
                                field.setAccessible(true);
                                field.set(activity, view);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

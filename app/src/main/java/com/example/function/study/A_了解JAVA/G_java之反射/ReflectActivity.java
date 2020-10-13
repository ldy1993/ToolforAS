package com.example.function.study.A_了解JAVA.G_java之反射;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.ldy.study.R;
import com.util.ClassUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum.学习功能主菜单;
import static com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum.反射例子;

@MenuActivity(menu=学习功能主菜单,sonMenu =反射例子)
public class ReflectActivity extends AppCompatActivity {
    private static final String TAG = "ReflectActivity";
    private boolean isReflect=false;
    public boolean isTrue(String str,int ret)
    {
        Log.d(TAG, "isTrue: "+str+ret);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            //Class对象是怎么来的呢。他是通过jvm 装载.class二进制文件.装载进了内存。后续会进行链接和初始化。装载只会执行一次。
            //1、在编译期间通过编译器获取Class对象。
            Class<?> clazz=ReflectActivity.this.getClass();
            //2、在运行期，直接在内存中获取Class对象，如果内存没有，就直接报ClassNotFoundException异常。
            Class claxx=Class.forName("com.example.function.study.A_了解JAVA.G_java之反射.ReflectActivity");
            //3、在运行期，去装载二进制文件class。如果已经装载。那么直接使用Class.forName
            Class clavv=getClassLoader().loadClass("com.example.function.study.A_了解JAVA.G_java之反射.ReflectActivity");

            //2.1、获取到他本类和父类的所有公共的成员变量。
            Field[]  fields= clazz.getFields();
            //2.2、获取到他本类所有成员变量。
            Field[]  declaredFields= clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Log.d(TAG, "onCreate_fields: "+fields[i]);
            }
            for (Field declaredField : declaredFields) {
                Log.d(TAG, "onCreate_declaredFields: "+declaredField);
            }

            //3.1、获取到他本类和父类的所有公共的方法。
            Method[]  methods= clazz.getMethods();
            //3.2、获取到他本类所有方法。
            Method[]  declaredMethods= clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                Log.d(TAG, "onCreate_methods: "+methods[i]);
            }
            for (Method declaredMethod : declaredMethods) {
                Log.d(TAG, "onCreate_declaredMethods: "+declaredMethod);
            }
            isReflect=  this.isTrue("设置为true",0);
            //int是基本数据类型。 Integer是一个object的派生类
           Method method= claxx.getMethod("isTrue",String.class,int.class);
           Object obj= method.invoke(this,"设置为true",0);
            Field  field= clavv.getField("isReflect");
            Log.d(TAG, "onResume_isReflect: "+field.get(this));
            field.set(this,obj);
            Log.d(TAG, "onResume_isReflect: "+field.get(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
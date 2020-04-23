package com.ldy.annotation;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.ldy.Utils.ReflactUtils;
import com.ldy.Utils.StringUtils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/12
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class AutoFindViewSolution implements AnnotationSolver {
    private Object obj;
    private Context context;
    private View viewHolder;
    private Object listener;

    public AutoFindViewSolution(Object viewDeclarer, Context context, View viewHolder, Object listener) {
        this.obj = viewDeclarer;
        this.context = context;
        this.viewHolder = viewHolder;
        this.listener = listener;
    }

    @Override
    public void solve() {
        Log.i("auto find view", "solve start");
        Field[] fields = ReflactUtils.getFieldsWithSuper(this.obj.getClass(), Object.class);
        Field[] var2 = fields;
        int var3 = fields.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            if (View.class.isAssignableFrom(field.getType())) {
                AutoFindView annotation = (AutoFindView)field.getAnnotation(AutoFindView.class);
                if (annotation != null) {
                    int id = annotation.value();
                    if (id == -1) {
                        try {
                            id = Class.forName(this.context.getPackageName() + ".R$id").getDeclaredField(StringUtils.upTo_(field.getName())).getInt((Object)null);
                        } catch (IllegalAccessException var29) {
                        } catch (NoSuchFieldException var30) {
                            try {
                                id = Class.forName(this.context.getPackageName() + ".R$id").getDeclaredField(field.getName()).getInt((Object)null);
                            } catch (IllegalAccessException var26) {
                                var26.printStackTrace();
                            } catch (NoSuchFieldException var27) {
                                var27.printStackTrace();
                                throw new RuntimeException("the auto find view id is not defined and the view's name cannot be found in R.id.class after 'upTo_' translate or not.");
                            } catch (ClassNotFoundException var28) {
                                var28.printStackTrace();
                                throw new RuntimeException("R.class not found, package: " + this.context.getPackageName());
                            }
                        } catch (ClassNotFoundException var31) {
                            var31.printStackTrace();
                            throw new RuntimeException("R.class not found, package: " + this.context.getPackageName());
                        }
                    }

                    field.setAccessible(true);
                    View view = null;

                    try {
                        Method method = null;
                        Class clazz = this.viewHolder.getClass();
                        NoSuchMethodException exception = null;

                        while(clazz != Object.class) {
                            try {
                                method = clazz.getDeclaredMethod("findViewById", Integer.TYPE);
                                if (method != null) {
                                    break;
                                }
                            } catch (NoSuchMethodException var32) {
                                clazz = clazz.getSuperclass();
                                exception = var32;
                            }
                        }

                        if (method == null) {
                            throw exception;
                        }

                        view = (View)method.invoke(this.viewHolder, id);
                    } catch (IllegalAccessException var33) {
                        var33.printStackTrace();
                        throw new RuntimeException("method findViewById in viewholder is not accessable");
                    } catch (InvocationTargetException var34) {
                        var34.printStackTrace();
                    } catch (NoSuchMethodException var35) {
                        var35.printStackTrace();
                        throw new RuntimeException("not found method findViewById in viewholder" + this.viewHolder.getClass().toString());
                    }

                    if (view == null) {
                        Log.i("auto find view", "can not find the view");
                    } else {
                        Log.i("auto find view", "view found");

                        try {
                            field.set(this.obj, view);
                        } catch (IllegalAccessException var25) {
                            var25.printStackTrace();
                        }

                        Class[] listenerTypes = annotation.listeners();
                        List<Method[]> methods = new ArrayList();
                        List<Class> clazzs = new ArrayList();
                        clazzs.add(view.getClass());

                        while(!((Class)clazzs.get(0)).equals(View.class)) {
                            clazzs.add(0, ((Class)clazzs.get(0)).getSuperclass());
                        }

                        Iterator var12 = clazzs.iterator();

                        while(var12.hasNext()) {
                            Class clazz = (Class)var12.next();
                            methods.add(clazz.getDeclaredMethods());
                        }

                        var12 = methods.iterator();

                        while(var12.hasNext()) {
                            Method[] methods1 = (Method[])var12.next();
                            Method[] var14 = methods1;
                            int var15 = methods1.length;

                            for(int var16 = 0; var16 < var15; ++var16) {
                                Method method = var14[var16];
                                Class[] var18 = listenerTypes;
                                int var19 = listenerTypes.length;

                                for(int var20 = 0; var20 < var19; ++var20) {
                                    Class listenerType = var18[var20];
                                    if (method.getParameterTypes().length == 1 && method.getParameterTypes()[0].equals(listenerType)) {
                                        try {
                                            method.setAccessible(true);
                                            method.invoke(view, this.listener);
                                        } catch (IllegalAccessException var23) {
                                            var23.printStackTrace();
                                            throw new RuntimeException("the setListener method not reached");
                                        } catch (InvocationTargetException var24) {
                                            var24.printStackTrace();
                                            throw new RuntimeException("invoke error");
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

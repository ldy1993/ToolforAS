package com.ldy.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/11/25
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class AnimationUtil {



        public static Field[] getFieldsWithSuper(Class clazz, Class rootSuper) {
            if (!rootSuper.isAssignableFrom(clazz)) {
                return null;
            } else {
                Field[] fields = new Field[0];
                Class temp = clazz;

                Field[] a;
                Field[] total;
                do {
                    a = temp.getDeclaredFields();
                    total = new Field[fields.length + a.length];
                    System.arraycopy(fields, 0, total, 0, fields.length);
                    System.arraycopy(a, 0, total, fields.length, a.length);
                    fields = total;
                } while((temp = temp.getSuperclass()) != rootSuper);

                a = temp.getDeclaredFields();
                total = new Field[total.length + a.length];
                System.arraycopy(fields, 0, total, 0, fields.length);
                System.arraycopy(a, 0, total, fields.length, a.length);
                return total;
            }
        }

        public static <T> T invokeStaticMethod(Class<?> clazz, String methodName, Object... args) {
            Class<?>[] types = new Class[args.length];

            for(int i = 0; i < types.length; ++i) {
                types[i] = args[i].getClass();
            }

            try {
                Method method = clazz.getDeclaredMethod(methodName, types);
                method.setAccessible(true);
                return (T) method.invoke((Object)null, args);
            } catch (NoSuchMethodException var5) {
                var5.printStackTrace();
            } catch (IllegalAccessException var6) {
                var6.printStackTrace();
            } catch (IllegalArgumentException var7) {
                var7.printStackTrace();
            } catch (InvocationTargetException var8) {
                var8.printStackTrace();
            }

            return null;
        }

        public static <T> T invoke(Object receiver, String methodName, Object... args) {
            Class<?>[] types = new Class[args.length];

            for(int i = 0; i < types.length; ++i) {
                types[i] = args[i].getClass();
            }

            try {
                Method method = receiver.getClass().getDeclaredMethod(methodName, types);
                method.setAccessible(true);
                return (T) method.invoke(receiver, args);
            } catch (NoSuchMethodException var5) {
                var5.printStackTrace();
            } catch (IllegalAccessException var6) {
                var6.printStackTrace();
            } catch (IllegalArgumentException var7) {
                var7.printStackTrace();
            } catch (InvocationTargetException var8) {
                var8.printStackTrace();
            }

            return null;
        }

        public static <T> T getFieldValue(Class<?> clazz, Object receiver, String fieldName) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return (T) field.get(receiver);
            } catch (NoSuchFieldException var5) {
                var5.printStackTrace();
            } catch (IllegalAccessException var6) {
                var6.printStackTrace();
            } catch (IllegalArgumentException var7) {
                var7.printStackTrace();
            }

            return null;
        }

        public static <T> T getStaticFieldValue(Class<?> clazz, String fieldName) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return (T) field.get((Object)null);
            } catch (NoSuchFieldException var4) {
                var4.printStackTrace();
            } catch (IllegalAccessException var5) {
                var5.printStackTrace();
            } catch (IllegalArgumentException var6) {
                var6.printStackTrace();
            }

            return null;
        }

        public static void setFieldValue(Class<?> clazz, Object receiver, String fieldName, Object value) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(receiver, value);
            } catch (NoSuchFieldException var6) {
                var6.printStackTrace();
            } catch (IllegalAccessException var7) {
                var7.printStackTrace();
            } catch (IllegalArgumentException var8) {
                var8.printStackTrace();
            }

        }

        public static void setStaticFieldValue(Class<?> clazz, String fieldName, Object value) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set((Object)null, value);
            } catch (NoSuchFieldException var5) {
                var5.printStackTrace();
            } catch (IllegalAccessException var6) {
                var6.printStackTrace();
            } catch (IllegalArgumentException var7) {
                var7.printStackTrace();
            }

        }

}

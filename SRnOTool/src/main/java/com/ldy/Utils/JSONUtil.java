package com.ldy.Utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/5/16
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class JSONUtil {



        public JSONUtil() {
        }

    /**
     * 开发于2019年7月8号
     * @param className
     * @param json
     * @return 返回一个className对应类名的类，这个类填充了json数据
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws UnsupportedEncodingException
     */
        public static Object toObject(String className, String json) throws InstantiationException, IllegalAccessException, ClassNotFoundException, UnsupportedEncodingException {
            JSONTokener jsonToken = new JSONTokener(json);

            JSONObject dataJson;
            try {
                dataJson = (JSONObject)jsonToken.nextValue();
            } catch (JSONException var15) {
                var15.printStackTrace();
                return null;
            }

            JSONObject extendData = dataJson;
            Object transData = Class.forName(className).newInstance();
            Class<?> demo = transData.getClass();
            Field[] fields = demo.getDeclaredFields();

            for(int i = 0; i < fields.length; ++i) {
                fields[i].setAccessible(true);
                String type = fields[i].getGenericType().toString();
                String name = fields[i].getName();
                if ("class java.lang.String".equals(type)) {
                    if (extendData != null) {
                        try {
                            String value = extendData.getString(name);
                            fields[i].set(transData, value);
                        } catch (JSONException var13) {
                            var13.printStackTrace();
                        }
                    }
                } else if ("int".equals(type) && extendData != null) {
                    try {
                        int value = extendData.getInt(name);
                        fields[i].setInt(transData, value);
                    } catch (JSONException var14) {
                        var14.printStackTrace();
                    }
                }
            }

            return transData;
        }


    }



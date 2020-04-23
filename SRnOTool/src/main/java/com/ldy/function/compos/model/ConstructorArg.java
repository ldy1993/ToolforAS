package com.ldy.function.compos.model;


import java.util.Map;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/7/27
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class ConstructorArg {
    private final static String TAG_DEBUG="ConstructorArg-调试态日志，生产关闭。by.ldy";

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getValue(Beans beans, Map<String, BeanData> beanDataMap, boolean flag) {
//        if(value!=null&&value.contains("${")&&value.contains("}"))
//        {
//            //${xxx}取出里面内容xxx
//            String valueTemp=value.substring(2,value.length()-1);
//            Log.e(TAG_DEBUG,"getValue,value:"+valueTemp);
//            BeanData receivedata=beanDataMap.get(valueTemp);
//            if(receivedata!=null)
//            {
//                if(receivedata.getData()!=null)
//                {
//                    String temp= Utils.bytes2HexString(receivedata.getData());
//                    Log.e(TAG_DEBUG,"BeanData:"+temp);
//                    return temp;
//                }
//            }
//            else
//            {
//                Bean bean=beans.getBeanMap().get(valueTemp);
//                if (bean!=null) {
//                    if(flag) {
//                        if (ComposReceiveService.beanParser(null, bean)) {
//                            Log.e(TAG_DEBUG, "发送读写" + bean.getId() + "成功");
//                        } else {
//                            Log.e(TAG_DEBUG, "发送读写" + bean.getId() + "失败");
//                            return null;
//                        }
//                    }else
//                    {
//                        if (ComposSendService.beanParser(bean)) {
//                            Log.e(TAG_DEBUG, "发送读写" + bean.getId() + "成功");
//                        } else {
//                            Log.e(TAG_DEBUG, "发送读写" + bean.getId() + "失败");
//                            return null;
//                        }
//                    }
//                     receivedata=beanDataMap.get(valueTemp);
//                    try {
//                         if (receivedata.getData() != null) {
//                             String temp =Utils.bytes2HexString(receivedata.getData());
//                             return temp;
//                         }
//                     }catch (Exception e)
//                     {
//                         e.printStackTrace();
//                         return null;
//                     }
//                }
//                else
//                {
//                    Log.e(TAG_DEBUG,"获取不到bean，返回空");
//                    return null;
//                }
//            }
//
//        }
//        return value;
//    }

    public void setValue(String value) {
        this.value = value;
    }
}

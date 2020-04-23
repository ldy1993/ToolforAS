package com.ldy.function.compos.model;


import com.ldy.Utils.ConvUtil;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/8/1
 * 描    述：存放接收发送的原生数据
 * 修订历史：
 * ================================================
 */
public class BeanData {
    private String bean_id;
    private String bean_name;
    /**
     * 接收数据
     */
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getBean_id() {
        return bean_id;
    }

    public void setBean_id(String bean_id) {
        this.bean_id = bean_id;
    }

    public String getBean_name() {
        return bean_name;
    }

    public void setBean_name(String bean_name) {
        this.bean_name = bean_name;
    }

    @Override
    public String toString() {
        try {
            return "BeanData{" +
                    "bean_id='" + bean_id + '\'' +
                    ", bean_name='" + bean_name + '\'' +
                    ", bean_data=" + ConvUtil.bytes2HexString(data) +
                    '}';
        } catch (Exception e) {
            return "BeanData{" +
                    "bean_id='" + bean_id + '\'' +
                    ", bean_name='" + bean_name + '\'' +
                    ", bean_data=" + "null" +
                    '}';
        }
    }
}

package com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu;

import com.ldy.study.R;

public enum SonMenuEnum {
    银行卡识别("银行卡识别", 0, R.drawable.ic_category_0),
    JNI测试("JNI测试", 0, R.drawable.ic_category_0),
    usb通讯("安卓端和qr55进行通讯", 0, R.drawable.ic_category_0),
    上传sn("上传sn，取得授权", 0, R.drawable.ic_category_0),
    交易通讯("交易通讯", 1, R.drawable.ic_category_0),
    了解JAVA界面("了解JAVA", 0, R.drawable.ic_category_0),
    线程操作("线程操作", 1, R.drawable.ic_category_0),
    反射例子("反射例子", 2, R.drawable.ic_category_0),
    界面跳转("界面跳转", 3, R.drawable.ic_category_0),
    创建控件("创建控件view和布局ViewGroup", 4, R.drawable.ic_category_0),
    自定义view("自定义view", 5, R.drawable.ic_category_0),
    基础ListView("基础ListView", 6, R.drawable.ic_category_0),
    可以左滑的listview("可以左滑的listview", 7, R.drawable.ic_category_0),
    进度条("进度条", 8, R.drawable.ic_category_0),
    对话框("对话框", 9, R.drawable.ic_category_0),
    POPUPWINDOW("POPUPWINDOW", 10, R.drawable.ic_category_0),
    基于线程池的异步任务("基于线程池的异步任务", 11, R.drawable.ic_category_0),
    异步任务下的网络请求("异步任务下的网络请求", 12, R.drawable.ic_category_0),
    静态fragment("静态fragment", 13, R.drawable.ic_category_0),
    设计模式("设计模式", 14, R.drawable.ic_category_0),
    RSA算法("RSA算法", 0, R.drawable.ic_category_0),
    sm2对称加密("sm2对称加密", 1, R.drawable.ic_category_0),
    sm4对称加密("sm4对称加密", 2, R.drawable.ic_category_0),
    登录界面("登录界面", 0, R.drawable.ic_category_0),
    菜单界面("菜单界面", 1, R.drawable.ic_category_0),
    输入金额界面("输入金额界面", 2, R.drawable.ic_category_0),
    COMPOS设置界面("COMPOS设置界面", 3, R.drawable.ic_category_0),
    推箱子("推箱子", 0, R.drawable.ic_category_0),
    日志上传("日志上传", 1, R.drawable.ic_category_0),
    保险箱计算密码("保险箱计算密码", 2, R.drawable.ic_category_0),
    异屏双显("异屏双显", 3, R.drawable.ic_category_0),
    播放广告("播放广告", 4, R.drawable.ic_category_0),
    MVC框架("MVC框架", 0, R.drawable.ic_category_0),
    黄油刀("黄油刀", 1, R.drawable.ic_category_0);
        private String title;
        private int index;
        private  int icon;
        SonMenuEnum(String title, int index, int icon) {
            this.title=title;
            this.index=index;
            this.icon=icon;
        }

    public String getTitle() {
        return title;
    }

    public int getIndex() {
        return index;
    }

    public int getIcon() {
        return icon;
    }
}
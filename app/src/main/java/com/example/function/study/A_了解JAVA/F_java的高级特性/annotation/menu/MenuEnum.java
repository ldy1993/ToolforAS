package com.example.function.study.A_了解JAVA.F_java的高级特性.annotation.menu;

import com.ldy.study.R;

public enum MenuEnum {
    图像识别主菜单("图像识别", 0, R.drawable.ic_category_0),
    NDK功能主菜单("NDK功能", 1, R.drawable.ic_category_10),
    调用其他app主菜单("调用其他app", 2, R.drawable.ic_category_20),
    外设接口主菜单("外设接口通讯", 3, R.drawable.ic_category_30),
    网络通讯主菜单("网络通讯功能", 4, R.drawable.ic_category_45),
    学习功能主菜单("学习功能", 5, R.drawable.ic_category_50),
    实例算法主菜单("实例算法", 6, R.drawable.ic_category_60),
    自定义界面主菜单("自定义界面", 7, R.drawable.ic_category_65),
    常用框架主菜单("手写常用框架", 8, R.drawable.ic_category_70),
    有趣功能主菜单("有趣的功能", 9, R.drawable.ic_category_80);

        private String title;
        private int index;
        private  int icon;
        MenuEnum(String title, int index, int icon) {
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
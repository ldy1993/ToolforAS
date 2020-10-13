package com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    private int icon;
    private String name;
    private Class<?> clazz;
    private List<MenuModel> list=new ArrayList<>();

    public MenuModel(String name, Class<?> clazz, int icon,List<MenuModel> list) {
        this.name=name;
        this.icon=icon;
        this.clazz=clazz;
        this.list=list;
    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public List<MenuModel> getList() {
        return list;
    }

    public void setList(List<MenuModel> list) {
        this.list = list;
    }
}

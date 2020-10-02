package com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu;

import android.app.Application;

import com.example.function.UndeterminedActivity;
import com.ldy.log.Log;
import com.ldy.study.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;


public class MenuActivityCompiler {
    private static final String TAG = "MenuActivityCompiler";
    public static final MenuModel defaultMenuModel=new MenuModel("待定", UndeterminedActivity.class, R.drawable.ic_category_0,null);

    public static List<MenuModel> menuModelList;

    /**
     * 对所有注解了menuActivity的类进行编译
     * @param application
     * @param pkgName
     */
    public static void menuActivityCompiler(Application application, String pkgName) {
        menuModelList = new ArrayList<>();
        //遍历该应用包名下，所有注解了MenuActivity的类。
        List<Class<?>> classList = getClass(application, pkgName);
        for (Class<?> aClass : classList) {
            if (aClass.isAnnotationPresent(MenuActivity.class)) {
                MenuActivity annotation = aClass.getAnnotation(MenuActivity.class);
                entryMenu(aClass, annotation);
            }

        }
    }

    /**
     * 录入一级菜单和二级菜单数据
     *
     * @param aClass
     * @param annotation
     */
    private static void entryMenu(Class<?> aClass, MenuActivity annotation) {
        MenuEnum menuEnum = annotation.menu();
        Log.e(TAG,"menuEnum.getTitle"+menuEnum.getTitle());
        SonMenuEnum sonMenuEnum = annotation.sonMenu();
//        判断当前的一级菜单List大小是否够大，不够大进行扩容。
        if (menuModelList.size() <= menuEnum.getIndex()) {
            //扩容
            expansion(menuModelList, menuEnum.getIndex());
            // 因为是扩容出来的所以需要先创建一个子菜单
            List<MenuModel> list = new ArrayList<>();
             getSonMenuModelList(aClass, sonMenuEnum, list);
            //创建一个新的一级菜单.
             createMenu(null, menuModelList, menuEnum.getTitle(), menuEnum.getIcon(), list, menuEnum.getIndex());
        } else {
            //判断以前录入过待定还是原来就有一级菜单的
            MenuModel menuModel = menuModelList.get(menuEnum.getIndex());
            List<MenuModel> list;
            if (!"待定".equals(menuModel.getName())) {
                //不是待定。以前录入过,sonMenuModel不为空
                list = menuModel.getList();
            } else {
                //以前没有录入过，新建一个List<MenuModel>
                list = new ArrayList<>();
            }
            getSonMenuModelList(aClass, sonMenuEnum, list);
             setMenu(aClass, menuModelList, menuEnum.getTitle(), menuEnum.getIcon(), list, menuEnum.getIndex());
        }
    }
    /**
     * 判断当前的二级菜单List大小是否够大，不够大进行扩容。扩容值为defaultMenuModel
     * @param aClass
     * @param sonMenuEnum
     * @param list
     */
    private static void getSonMenuModelList(Class<?> aClass, SonMenuEnum sonMenuEnum, List<MenuModel> list) {
        if (list.size() <= sonMenuEnum.getIndex()) {
             expansion(list, sonMenuEnum.getIndex());
            //创建一个新的二级菜单,添加多一个
             createMenu(aClass, list, sonMenuEnum.getTitle(), sonMenuEnum.getIcon(), null, sonMenuEnum.getIndex());

        } else {
            //创建一个新的二级菜单，设置
             setMenu(aClass, list, sonMenuEnum.getTitle(), sonMenuEnum.getIcon(), null, sonMenuEnum.getIndex());

        }
    }

    /**
     * 创建一个菜单,添加
     *
     * @param aClass
     * @param MenuList
     * @param title
     * @param icon
     * @param sonMenuList
     * @param index
     */
    private static void createMenu(Class<?> aClass, List<MenuModel> MenuList, String title, int icon, List<MenuModel> sonMenuList, int index) {
        MenuModel menuModel = new MenuModel(title, aClass, icon, sonMenuList);
        MenuList.add(index, menuModel);
    }

    /**
     * 创建一个菜单，设置
     *
     * @param aClass
     * @param MenuList
     * @param title
     * @param icon
     * @param sonMenuList
     * @param index
     */
    private static void setMenu(Class<?> aClass, List<MenuModel> MenuList, String title, int icon, List<MenuModel> sonMenuList, int index) {
        MenuModel menuModel = new MenuModel(title, aClass, icon, sonMenuList);
        MenuList.set(index, menuModel);
    }
    /**
     * 扩容值为defaultMenuModel
     *
     * @param menuModelList
     * @param index         扩容到index这么大
     */
    private static void expansion(List<MenuModel> menuModelList, int index) {
        int size = menuModelList.size();
        for (int i = size; i < index; i++) {
            menuModelList.add( i, defaultMenuModel);
        }
    }

    private static List<Class<?>> getClass(Application application, String packageName) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        try {

            DexFile df = new DexFile(application.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = (String) enumeration.nextElement();

                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    android.util.Log.e("ldy", "获取到的类名：" + className);
                    try {
                        classList.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classList;
    }

}

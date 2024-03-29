package com.util;

import android.util.Log;

import com.example.function.study.A_了解JAVA.TestActivity;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {
    /**
     * @param pkgName    遍历的包名
     * @param annotation 某些注解class的过滤项，即可只选出所有注解了该类的所有class
     * @return
     */
    public static List<Class<?>> getClassList(String pkgName, Class<? extends Annotation> annotation) {
        return getClassList(pkgName, true, annotation);
    }

    /**
     * @param pkgName     遍历的包名
     * @param isRecursive 标识是否要遍历该包路径下子包的类名
     * @param annotation  某些注解class的过滤项，即可只选出所有注解了该类的所有class
     * @return
     */
    public static List<Class<?>> getClassList(String pkgName, boolean isRecursive, Class<? extends Annotation> annotation) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
       ClassLoader  loader = Thread.currentThread().getContextClassLoader();
        try {
            // 按文件的形式去查找
            String strFile = pkgName.replaceAll("\\.", "/");
            // 定义一个枚举的集合 并进行循环来处理这个目录下的things
            Log.e("ldy","开始");
            System.out.println(TestActivity.class.getClass().getClassLoader().getResource(""));
            System.out.println(loader.getResource(strFile));
            Enumeration<URL> urls = loader.getResources(strFile);
            while (urls.hasMoreElements()) {
                // 获取下一个元素
                URL url = urls.nextElement();
                if (url != null) {
                    // 得到协议的名称
                    String protocol = url.getProtocol();
                    String pkgPath = url.getPath();
                    System.out.println("protocol:" + protocol + " path:" + pkgPath);
                    if ("file".equals(protocol)) {
                        // 本地自己可见的代码
                        findClassName(classList, pkgName, pkgPath, isRecursive, annotation);
                    } else if ("jar".equals(protocol)) {
                        // 引用第三方jar的代码
                        findClassName(classList, pkgName, url, isRecursive, annotation);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classList;
    }

    public static void findClassName(List<Class<?>> clazzList, String pkgName, String pkgPath, boolean isRecursive, Class<? extends Annotation> annotation) {
        if (clazzList == null) {
            return;
        }
        // 过滤出.class文件及文件夹
        File[] files = filterClassFiles(pkgPath);
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                //判断是文件还是文件夹
                if (file.isFile()) {
                    String clazzName = getClassName(pkgName, fileName);
                    addClassName(clazzList, clazzName, annotation);
                } else {
                    if (isRecursive) {
                        // 需要继续查找该文件夹/包名下的类
                        String subPkgName = pkgName + "." + fileName;
                        String subPkgPath = pkgPath + "/" + fileName;
                        findClassName(clazzList, subPkgName, subPkgPath, true, annotation);
                    }
                }
            }
        }
    }

    /**
     * 第三方Jar类库的引用。<br/>
     *
     * @throws IOException
     */
    public static void findClassName(List<Class<?>> clazzList, String pkgName, URL url, boolean isRecursive, Class<? extends Annotation> annotation) throws IOException {
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        System.out.println("jarFile:" + jarFile.getName());
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName(); // 类似：sun/security/internal/interfaces/TlsMasterSecret.class
            String clazzName = jarEntryName.replace("/", ".");
            int endIndex = clazzName.lastIndexOf(".");
            String prefix = null;
            if (endIndex > 0) {
                String prefix_name = clazzName.substring(0, endIndex);
                endIndex = prefix_name.lastIndexOf(".");
                if (endIndex > 0) {
                    prefix = prefix_name.substring(0, endIndex);
                }
            }
            if (prefix != null && jarEntryName.endsWith(".class")) {
//                System.out.println("prefix:" + prefix +" pkgName:" + pkgName);
                if (prefix.equals(pkgName)) {
                    System.out.println("jar entryName:" + jarEntryName);
                    addClassName(clazzList, clazzName, annotation);
                } else if (isRecursive && prefix.startsWith(pkgName)) {
                    // 遍历子包名：子类
                    System.out.println("jar entryName:" + jarEntryName + " isRecursive:" + isRecursive);
                    addClassName(clazzList, clazzName, annotation);
                }
            }
        }
    }

    private static File[] filterClassFiles(String pkgPath) {
        if (pkgPath == null) {
            return null;
        }
        // 接收 .class 文件 或 类文件夹
        return new File(pkgPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
    }

    private static String getClassName(String pkgName, String fileName) {
        int endIndex = fileName.lastIndexOf(".");
        String clazz = null;
        if (endIndex >= 0) {
            clazz = fileName.substring(0, endIndex);
        }
        String clazzName = null;
        if (clazz != null) {
            clazzName = pkgName + "." + clazz;
        }
        return clazzName;
    }

    private static void addClassName(List<Class<?>> clazzList, String clazzName, Class<? extends Annotation> annotation) {
        if (clazzList != null && clazzName != null) {
            Class<?> clazz = null;
            try {
                //这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
//                clazz = Class.forName(clazzName);
                clazz = Thread.currentThread().getContextClassLoader().loadClass(clazzName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (clazz != null) {
                if (annotation == null) {
                    clazzList.add(clazz);
                    System.out.println("add:" + clazz);
                } else if (clazz.isAnnotationPresent(annotation)) {
                    clazzList.add(clazz);
                    System.out.println("add annotation:" + clazz);
                }
            }
        }
    }
}
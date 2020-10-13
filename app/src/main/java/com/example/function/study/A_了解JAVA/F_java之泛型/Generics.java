package com.example.function.study.A_了解JAVA.F_java之泛型;

import com.ldy.log.Log;

/**
 * 泛型的实现类
 */
public class Generics<T> extends AGenerics<String,String,String> {
    private T item;
    public Generics(T t){item=t;}
    public void set(T t){item=t;}
    public T get(){return item;}
    @Override
    public String[] add(String... e) {
        for (String s : e) {
            Log.e("tag",s);
        }
        return null;
    }

    @Override
    public void put(Integer key, String value){

    }
}

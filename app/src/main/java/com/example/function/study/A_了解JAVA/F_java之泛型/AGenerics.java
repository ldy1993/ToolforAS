package com.example.function.study.A_了解JAVA.F_java之泛型;

/**
 * 泛型的抽象类
 * @param <T>
 * @param <E>
 * @param <V>
 */
public abstract class AGenerics<T,E,V> implements IGenerics<T,E,Integer,V> {
    @Override
    public T[] add(E... e) {
        return null;
    }

    @Override
    public void put(Integer key, V value) {

    }
}

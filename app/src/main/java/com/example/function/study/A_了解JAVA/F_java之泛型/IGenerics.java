package com.example.function.study.A_了解JAVA.F_java之泛型;

/**
 * 泛型的接口类
 * @param <T>
 * @param <E>
 * @param <K>
 * @param <V>
 */
public interface IGenerics<T,E,K,V> {
    T[] add(E... e);
    void put(K key,V value);
}

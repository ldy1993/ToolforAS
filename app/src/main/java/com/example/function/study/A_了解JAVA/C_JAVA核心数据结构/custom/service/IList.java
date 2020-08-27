package com.example.function.study.A_了解JAVA.C_JAVA核心数据结构.custom.service;

/**
 * 表顶级接口
 * @author 东阳
 */
public interface IList<T> {
    /**
     * 判断表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 表长度
     * @return
     */
    int size();

    /**
     * 获取元素
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 设置某个结点的的值
     * @param index
     * @param data
     * @return
     */
    T set(int index, T data);

    /**
     * 根据index添加结点
     * @param index
     * @param data
     * @return
     */
    boolean add(int index, T data);

    /**
     * 添加结点
     * @param data
     * @return
     */
    boolean add(T data);

    /**
     * 根据index移除结点
     * @param index
     * @return
     */
    T remove(int index);

    /**
     * 根据data移除结点
     * @param data
     * @return
     */
    boolean remove(T data);

    /**
     * 清空链表
     */
    void clear();

    /**
     * 是否包含data结点
     * @param data
     * @return
     */
    boolean contains(T data);
    /**
     * 获取某值第一次出现的索引
     * 分析：时间复杂度O(n)
     */
    int indexOf(T data);
      /**
     * 输出格式
     * @return
     */
      @Override
      String toString();
}
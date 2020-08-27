package com.example.function.study.A_了解JAVA.C_JAVA核心数据结构.custom.service.impl;

import com.example.function.study.A_了解JAVA.C_JAVA核心数据结构.custom.service.IList;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/8/18
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class SRnOArrayList<T> implements IList<T> {

    private T[] elementData;
    /**
     * 默认的情况下，大小为10，不够在进行扩容
     */
    private int initialCapacity=10;
    private int theSize;

    public SRnOArrayList() {
        this(10);
    }

    public SRnOArrayList(int initialCapacity) {
        //如果列表大小设置小于0，会报出异常
        if (initialCapacity < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.theSize = 0;
        this.initialCapacity=initialCapacity;
        elementData = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean isEmpty() {
        return elementData.length == 0;
    }

    @Override
    public int size() {
        return theSize;
    }

    /**
     * 获取指定位置的元素
     * 分析：时间复杂度O(1)
     * 从顺序表中检索值是简单高效的，因为顺序表内部采用数组作为容器，
     * 数组可直接通过索引值访问元素
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return elementData[index];
    }
    /**
     * 为指定索引的结点设置值,返回该索引原有的值
     * 分析：时间复杂度O(1)
     */
    @Override
    public T set(int index, T data) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        T oldValue = elementData[index];
        elementData[index] = data;
        return oldValue;
    }

    /**
     * 指定位置插入元素
     * 分析：时间复杂度O(n)
     *
     * @param index
     * @param data
     * @return
     */
    @Override
    public boolean add(int index, T data) {
        if (index > elementData.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //判断是否需要扩容
        if(elementData.length==size()) {
            increaseSpace(size() * 2);
        }
        //把index位置后的元素从右向左依次右移，并使theSize加一
        for(int i=size();i>index;i--){
            elementData[i+1]=elementData[i];
        }
        elementData[index]=data;
        theSize++;
        return true;
    }

    /**
     * 在顺序表末尾处插入元素
     * 分析：时间复杂度O(n)
     *
     * @param data
     * @return
     */
    @Override
    public boolean add(T data) {
        add(size(),data);
        return true;
    }

    /**
     * //扩容
     * @param newSpace
     */
    public void increaseSpace(int newSpace){
        //if(newSpace<theSize)
        //return;
        T temp[]=elementData;
        elementData=(T[]) new Object[newSpace];
        for(int i=0;i<size();i++){
            elementData[i]=temp[i];
        }

    }
    /**
     * 移除指定索引的元素
     * 分析：时间复杂度O(n)
     * 此处由于数组元素数量-1，所以需要创建新数组。
     * ArrayList由于是动态数组（list.size()≠data.length），所以只需要将删除的元素之后的前移一位
     *
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        T oldValue=elementData[index];
        //将index位置后的元素从左向右依次左移，并使theSize减一
        for(int i=index;i<size()-1;i++){
            elementData[i]=elementData[i+1];
        }
        theSize--;
        return oldValue;
    }

    @Override
    public boolean remove(T data) {
        if (data == null) {
            for (int index = 0; index < size(); index++) {
                if (elementData[index] == null) {
                    remove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < elementData.length; index++) {
                if (data.equals(elementData[index])) {
                    remove(index);
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void clear() {
        theSize=0;
        //对数组初始化，扩容为10
        increaseSpace(initialCapacity);
    }

    /**
     * 判断是否包含某值只需要判断该值有没有出现过
     * 分析：时间复杂度O(n)
     */
    @Override
    public boolean contains(T data) {
        return indexOf(data) >= 0;
    }

    /**
     * 获取某值第一次出现的索引
     * 分析：时间复杂度O(n)
     */
    @Override
    public int indexOf(T data) {
        if (data == null) {
            for (int i = 0; i < size(); i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i <size(); i++) {
                if (data.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        String str = "[";
        for (int i = 0; i < elementData.length; i++) {
            str += (elementData[i] + ", ");
        }
        str = str.substring(0, str.lastIndexOf(", "));
        return str + "]";
    }
}

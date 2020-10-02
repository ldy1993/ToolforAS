package com.example.function.study.F_数据结构.custom.service.impl;

import com.example.function.study.F_数据结构.custom.model.Node;
import com.example.function.study.F_数据结构.custom.service.IList;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/8/18
 * 描    述：带头结点的单链表
 * 1、更快删除/插入第一个结点
 * 2、统一空表和非空表的处理
 * 修订历史：
 * ================================================
 */
public class SingleTrackLinkedList<T> implements IList<T> {
    private Node head;
    /**
     * 一共几个结点
     */
    private int size = 0;

    public SingleTrackLinkedList() {
        this.head = new Node(null);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 获取索引所在的节点的数据
     * 效率低 O(n)
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        //如果index位置错误，抛异常
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        //和顺序表不一样，不能通过索引直接计算定位，需要从头结点开始查找
        Node p = getNode(index);
        return (T) p.getData();
    }

    /**
     * 设置索引所在的节点的数据
     * 效率低 O(n)
     * @param index
     * @param data
     * @return
     */
    @Override
    public T set(int index, T data) {
        //如果index位置错误，抛异常
        if (index < 0 || index >=size) {
            throw new IndexOutOfBoundsException();
        }
        //获取索引所在的节点
        Node p = getNode(index);
        T oldValue = (T) p.getData();
        //修改该节点的数据
        p.setData(data);
        return oldValue;
    }

    /**
     * 设置索引所在的节点的数据
     * 效率低 O(n)，但是少了移动数据时间。即链表插入只需要找到位置，不需要对后续数据进行移动
     * @param index
     * @param data
     * @return
     */
    @Override
    public boolean add(int index, T data) {
        //如果index位置错误，抛异常
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        //查找前一个结点，从head结点开始
        Node p = getNode(index - 1);
        //新创建一个结点
        Node newNode = new Node(data);
        //指明新结点的后驱结点
        newNode.setNext(p.getNext());
        //指明新结点的前驱结点
        p.setNext(newNode);
        //数量+1
        size++;
        return true;
    }

    @Override
    public boolean add(T data) {
        this.add(size, data);
        return true;
    }

    @Override
    public T remove(int index) {
        //如果index位置错误，抛异常
        if (index < 0 || index>=size) {
            throw new IndexOutOfBoundsException();
        }
        //查找到前一个结点，从head结点开始
        Node p = getNode(index - 1);
        //需要删除的节点
        Node deleteNode = p.getNext();
        //需要删除的节点的前一个结点设置后驱为需要删除的节点的后一个结点
        p.setNext(deleteNode.getNext());
        T data = (T) deleteNode.getData();
        deleteNode.setData(null);
        size--;
        return data;
    }

    @Override
    public boolean remove(T data) {
        int index=indexOf(data);
        remove(index);
        size--;
        return false;
    }

    /**
     * 只清除头节点以外的链表
     */
    @Override
    public void clear() {
        Node p=head.getNext();
        for (int j = 0; j < size; j++) {
            Node nextNode=p.getNext();
            p.setData(null);
            p.setNext(null);
            p=nextNode;
        }
        size=0;
    }
    /**
     * 销毁整个链表
     */
    public void destroy()
    {
        clear();
        head=null;
    }
    @Override
    public boolean contains(T data) {

        return indexOf(data)>=0;
    }

    @Override
    public String toString() {
        if (size == 0){
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        Node p = head.getNext();
        for (int i = 0;i<size;i++){
            if (i!=size-1){
                builder.append(p.getData()+",");
            }else {
                builder.append(p.getData());
            }
            //移动到下一个结点
            p=p.getNext();
        }
        builder.append("]");
        return builder.toString() ;
    }

    /**
     * 获取某值第一次出现的索引
     * 分析：时间复杂度O(n)
     */
    @Override
    public int indexOf(T data) {
        if (data == null) {
            Node p = head;
            for (int j = 0; j < size + 1; j++) {
                if (p.getData() == null) {
                    return j;
                }
                p = p.getNext();
            }
        } else {
            Node p = head;
            for (int j = 0; j < size + 1; j++) {
                if (data.equals(p.getData())) {
                    return j;
                }
                p = p.getNext();
            }
        }
        return  -1;
    }

    /**
     * 查找当前结点，从head结点开始
     *
     * @param index
     * @return
     */
    private Node getNode(int index) {
        Node p = head;
        for (int j = 0; j < index + 1; j++) {
            p = p.getNext();
        }
        return p;
    }

}

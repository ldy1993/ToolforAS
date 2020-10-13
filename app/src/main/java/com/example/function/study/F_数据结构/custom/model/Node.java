package com.example.function.study.F_数据结构.custom.model;

/**
 * 单向链表节点
 * @author 东阳
 */
public class Node<T> {
    public T data;//数据域
    public Node<T> next;//地址域

    public Node(T data){
        this.data=data;
    }

    public Node(T data,Node<T> next){
        this.data=data;
        this.next=next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
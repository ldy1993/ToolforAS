package com.example.function.study.A_了解JAVA.C_JAVA核心数据结构;

import com.example.function.study.A_了解JAVA.C_JAVA核心数据结构.custom.service.impl.SRnOArrayList;
import com.example.function.study.A_了解JAVA.C_JAVA核心数据结构.custom.service.impl.SingleTrackLinkedList;
import com.ldy.log.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/8/18
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class 线性数据结构 {
    /**
     * 一维数组
     *  数组这种数据结构典型的操作方法，是根据下标进行操作的，
     *  所以insert的的时候可以根据下标插入到具体的某个位置，
     *  但是这个时候它后面的元素都得往后面移动一位。
     *  所以插入效率比较低,更新，删除效率也比较低，而查询效率非常高,
     *  查询效率时间复杂度是1。
     */
    public void linearArray()
    {
        //  1：动态初始化：定义和分配空间可以分开进行。也可以同时进行。
        int[] intArray;
        intArray=new int[1024*1024];
        //  2：静态初始化：只能声明和初始化一同进行，放到同一行。
        int[] intArray1 = {1,2,3};
        //   3：动静结合：
        int[] intArray2 = new int[]{1,2,3};

        System.out.print( System.currentTimeMillis()+"一维数组:"+"插入开始\n");
        for (int j=0;j<intArray.length;j++)
        {
            intArray[j]=j;
        }
        System.out.print( System.currentTimeMillis()+":"+"插入结束，查询开始\n");
        for (int i : intArray) {
            int k=i;
        }
        System.out.print( System.currentTimeMillis()+"一维数组:"+"查询结束\n");
    }
    /**
     * 自定义顺序线性表
     */
    public void arrayList()
    {
        SRnOArrayList<Integer> arrayList  =new SRnOArrayList<>(1024*1024);
        System.out.print( System.currentTimeMillis()+"顺序线性表:"+"插入开始\n");
        for (int j=0;j<1024*1024;j++)
        {
            arrayList.add(j);
        }
        System.out.print( System.currentTimeMillis()+":"+"插入结束，查询开始\n");
        for (int i = 0; i < arrayList.size(); i++) {
            int k=  arrayList.get(i);
        }
        System.out.print( System.currentTimeMillis()+"顺序线性表:"+"查询结束\n");
    }
    /**
     * 系统双向链式线性表
     */
    public void linkedList()
    {
        LinkedList linkedList=new LinkedList();
        System.out.print( System.currentTimeMillis()+"链式线性表:"+"插入开始\n");
        for (int j=0;j<1024*1024;j++)
        {
            linkedList.add(j);
        }
        System.out.print( System.currentTimeMillis()+":"+"插入结束，查询开始\n");
        for (int i = 0; i < linkedList.size(); i++) {
           int k= (int) linkedList.get(i);
        }
        System.out.print( System.currentTimeMillis()+"链式线性表:"+"查询结束\n");
    }
    /**
     * 自定义单向链式线性表
     */
    public void linkedList1()
    {
        SingleTrackLinkedList linkedList=new SingleTrackLinkedList();
        System.out.print( System.currentTimeMillis()+"链式线性表:"+"插入开始\n");
        for (int j=0;j<1024*1024;j++)
        {
            linkedList.add(j);
        }
        System.out.print( System.currentTimeMillis()+":"+"插入结束，查询开始\n");
        for (int i = 0; i < linkedList.size(); i++) {
            int k= (int) linkedList.get(i);
        }
        System.out.print( System.currentTimeMillis()+"链式线性表:"+"查询结束\n");
        linkedList.clear();
        System.out.print( System.currentTimeMillis()+"链式线性表:"+"清除结束\n");

    }
}

package com.example.function.study.A_了解JAVA.H_java之注解.annotation.xml;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/13
 * 描    述：
 * 修订历史：
 * ================================================
 */
//如果SOURCE-->编译器。对java文件生效。
// 如果是Class的话。就是在存储器中的.class二进制文件中生效。
//如果是RUNTIME--》运行时，就是对内存中的对象生效。
@Retention(RetentionPolicy.RUNTIME)
@interface XStreamRoot {
}

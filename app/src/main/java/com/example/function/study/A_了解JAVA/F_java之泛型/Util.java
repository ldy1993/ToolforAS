package com.example.function.study.A_了解JAVA.F_java之泛型;

import com.example.function.study.A_了解JAVA.A_Java的四个基本特性.Bird;
import com.example.function.study.A_了解JAVA.A_Java的四个基本特性.Eagles;
import com.example.function.study.A_了解JAVA.A_Java的四个基本特性.Spadger;

public class Util {
    public void main()
    {
        Eagles eagles=new Eagles();
        Spadger spadger=new Spadger();
        //extends例子
        Generics<? extends Bird> genericsEagles = new Generics<Eagles>(eagles);
        Generics<? extends Bird> genericsSpadger = new Generics<Spadger>(spadger);
        Generics<Bird> generics = new Generics<Spadger>(spadger);
        Generics<Spadger> generics1 = new Generics<Spadger>(spadger);
        Generics<?> generics2 = new Generics<Spadger>(spadger);
        genericsEagles.get();
        genericsSpadger.get();
        genericsSpadger.set(spadger);

        //super例子
        Generics<? super Spadger> genericsSpadgerSuper = new Generics<Bird>(new Bird() {
            @Override
            public void eat() {

            }
        });
        Generics<Spadger> genericsSpadger1 = new Generics<Bird>(new Bird() {
            @Override
            public void eat() {

            }
        });
    }
}

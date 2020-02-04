package com.example.function.study.了解JAVA.设计过程中的六个基本原则.最少知识原则LKP.符合;

import com.ldy.function.Log.Log;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/4
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class Friend {
    private final String friend;

    public Friend(String friend) {
        this.friend=friend;
    }

    public void friend()
    {
        Log.e("ldy","Friend类有朋友"+friend);
    }
}

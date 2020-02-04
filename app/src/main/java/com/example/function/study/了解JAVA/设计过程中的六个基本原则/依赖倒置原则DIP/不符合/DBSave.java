package com.example.function.study.了解JAVA.设计过程中的六个基本原则.依赖倒置原则DIP.不符合;

import com.ldy.function.Log.Log;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/3
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class DBSave {
    //这里有人可能会说不符合单一职责，但是对于数据库来说，保存插入一条新数据，
    // 里面包含多个字段其实是可以用一句jobc解决的，视为一个单一功能
    public void saveMsgFromWeb(String Name, String Pwd)
    {
        Log.e("ldy","用户名:"+Name+";密码"+Pwd+";注册类型：网页，"+"保存成功，不符合DIP");
    }

    public void saveMsgFromPhone(String name, String pwd) {
        Log.e("ldy","用户名:"+name+";密码"+pwd+";注册类型：手机，"+"保存成功，不符合DIP");
    }
}

package com.example.function.study.了解JAVA.设计过程中的六个基本原则.依赖倒置原则DIP.符合;

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
public class DbSaveFromWeb extends DbSave{

    @Override
    public void saveMsg(String Name, String Pwd)
    {
        Log.e("ldy","用户名:"+Name+";密码"+Pwd+";注册类型：网页"+"保存成功，符合DIP");
    }

}

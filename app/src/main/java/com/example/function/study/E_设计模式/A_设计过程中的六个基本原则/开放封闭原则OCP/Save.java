package com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.开放封闭原则OCP;

import com.ldy.log.Log;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/3
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class Save {
    public void saveMsg(User user)
    {
        Log.e("ldy","用户名:"+user.getName()+";密码"+user.getPwd()+";账号"+user.getAccount()+";注册类型：网页，"+"保存成功，不符合DIP");
    }
}

package com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.依赖倒置原则DIP.不符合;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/3
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class Register {
    public void registerFromWeb(DBSave dbSave,String Name,String Pwd) {
        dbSave.saveMsgFromWeb(Name,Pwd);
    }
    public void registerFromPhone(DBSave dbSave,String Name,String Pwd) {
        dbSave.saveMsgFromPhone(Name,Pwd);
    }
}

package com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.依赖倒置原则DIP.符合;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/4
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class RegisterUser extends RegisterAbstract {
    private DbSave dbSave;

    public RegisterUser(DbSave dbSave) {
        this.dbSave = dbSave;
    }

    @Override
    public void setDbSave(DbSave dbSave) {
        this.dbSave = dbSave;
    }
    @Override
    public void register(DbSave dbSave, String Name, String Pwd) {
        dbSave.saveMsg(Name, Pwd);
    }

}

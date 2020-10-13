package com.example.function.study.A_了解JAVA;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.function.study.A_了解JAVA.A_Java的四个基本特性.Eagles;
import com.example.function.study.A_了解JAVA.A_Java的四个基本特性.Spadger;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.依赖倒置原则DIP.不符合.DBSave;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.依赖倒置原则DIP.不符合.Register;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.依赖倒置原则DIP.符合.DbSaveFromPhone;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.依赖倒置原则DIP.符合.DbSaveFromWeb;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.依赖倒置原则DIP.符合.RegisterUser;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.单一职责原则SRP.代码级别不符合SRP.NetworkCode;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.单一职责原则SRP.方法级别不符合SRP.NetworkMethod;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.单一职责原则SRP.符合SRP.GetParam;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.单一职责原则SRP.符合SRP.PostFile;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.单一职责原则SRP.符合SRP.PostParam;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.开放封闭原则OCP.Save;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.开放封闭原则OCP.User;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.接口隔离原则ISP.符合.Bird;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.接口隔离原则ISP.符合.Person;
import com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.里氏替换原则LSP.Ostrich;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.Compiler;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.FindViewById;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.OnBind;
import com.ldy.log.Log;
import com.ldy.study.R;

import static com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum.学习功能主菜单;
import static com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum.了解JAVA界面;


@MenuActivity(menu=学习功能主菜单,sonMenu =了解JAVA界面)
public class TestActivity extends Activity {
    @OnBind("临时值")
    private String temp;
    @OnBind
    private String defaultValue;
    @FindViewById(R.id.bt_basic_characteristics)
    public Button bt_basic_characteristics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Compiler.onBindCompiler(this);
        Compiler.findViewByIdCompiler(this);
        bt_basic_characteristics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eagles eagles = new Eagles();
                Spadger spadger = new Spadger();
                Log.i("ldy", eagles.name + "有" + eagles.windNub + "翅膀");
                eagles.eat();
                eagles.eat("秃鹰");
                Log.i("ldy", spadger.name + "有" + spadger.windNub + "翅膀");
                spadger.eat();
            }
        });
        //接口隔离原则
        Button bt_ISP_SUC = findViewById(R.id.bt_ISP_SUC);
        bt_ISP_SUC.setOnClickListener(ISPSucListener);
        //单一职责原则
        Button bt_Code_SRP_Err = findViewById(R.id.bt_Code_SRP_Err);
        bt_Code_SRP_Err.setOnClickListener(CodeSRPErrListener);
        Button bt_SRP_SUC = findViewById(R.id.bt_SRP_SUC);
        bt_SRP_SUC.setOnClickListener(SRPSucListener);
        Button bt_Method_SRP_Err = findViewById(R.id.bt_Method_SRP_Err);
        bt_Method_SRP_Err.setOnClickListener(MethodSRPErrListener);
        //里式替换原则
        Button bt_LSP_ERR1 = findViewById(R.id.bt_LSP_ERR1);
        bt_LSP_ERR1.setOnClickListener(LSPErr1Listener);
        Button bt_LSP_ERR2 = findViewById(R.id.bt_LSP_ERR2);
        bt_LSP_ERR2.setOnClickListener(LSPErr2Listener);
        //里式替换原则
        Button bt_DIP_conform = findViewById(R.id.bt_DIP_conform);
        bt_DIP_conform.setOnClickListener(DIPConform1Listener);
        Button bt_DIP_inconformity = findViewById(R.id.bt_DIP_inconformity);
        bt_DIP_inconformity.setOnClickListener(DIPINCONFORMITYListener);
        Button bt_OCP = findViewById(R.id.bt_OCP);
        bt_OCP.setOnClickListener(btOCPListener);

    }


    View.OnClickListener ISPSucListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bird bird = new Bird();
            Person person = new Person();
            bird.fly();
            bird.running();
            person.wave();
            person.running();
        }
    };
    View.OnClickListener SRPSucListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GetParam getParam = new GetParam();
            getParam.execute();
            PostFile postFile = new PostFile();
            postFile.execute();
            PostParam postParam = new PostParam();
            postParam.execute();
        }
    };
    View.OnClickListener MethodSRPErrListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NetworkMethod networkMethod = new NetworkMethod();
            networkMethod.getParam();
            networkMethod.postFile();
            networkMethod.postParam();
        }
    };
    View.OnClickListener CodeSRPErrListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NetworkCode networkCode = new NetworkCode();
            networkCode.execute(NetworkCode.GET_PARAM);
            networkCode.execute(NetworkCode.POST_FILE);
            networkCode.execute(NetworkCode.POST_PARAM);
        }
    };
    View.OnClickListener LSPErr1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Ostrich ostrich = new Ostrich();
            ostrich.running();
        }
    };
    View.OnClickListener LSPErr2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Ostrich ostrich = new Ostrich();
            ostrich.fly();
        }
    };
    View.OnClickListener DIPConform1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DbSaveFromPhone dbSaveFromPhone = new DbSaveFromPhone();
            DbSaveFromWeb dbSaveFromWeb = new DbSaveFromWeb();
            RegisterUser register = new RegisterUser(dbSaveFromPhone);
            register.setDbSave(dbSaveFromPhone);
            register.register(dbSaveFromPhone, "张三", "123456");
            register = new RegisterUser(dbSaveFromWeb);
            register.setDbSave(dbSaveFromWeb);
            register.register(dbSaveFromWeb, "李四", "654321");
        }
    };
    View.OnClickListener DIPINCONFORMITYListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Register register = new Register();
            DBSave dbSave = new DBSave();
            register.registerFromWeb(dbSave, "张三", "123456");
            register.registerFromPhone(dbSave, "李四", "654321");
        }
    };
    View.OnClickListener btOCPListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Save save = new Save();
            User user = new User();
            user.setName("王五");
            user.setPwd("321654");
            user.setAccount("7373473");
            save.saveMsg(user);
        }
    };

}

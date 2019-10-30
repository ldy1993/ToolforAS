package com.example.main;

import com.ldy.study.R;

import java.util.HashMap;

/**
 * ================================================
 *
 * @author ldy
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/10/27
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class ConstantData {
    private final static String FUNCTION_CALL_OTHER_APP = "调用其它app";
    private final static String CALL_OTHER_APP_CCB = "调用建行app，版权关系，分支为私有库";
    private final static String CALL_OTHER_APP_CGB = "调用广发app，版权关系，分支为私有库";
    private final static String CALL_OTHER_APP_APPT = "调用APPT";
    private final static String FUNCTION_SCAN = "调用摄像头功能";
    private final static String SCAN_IDCARD = "银行卡识别";
    private final static String FUNCTION_JNI = "JNI编程";
    private final static String JNI_TEST = "JNI测试";
    private final static String FUNCTION_USB = "USB通讯功能";
    private final static String USB_HOST_QR55 = "安卓端和qr55进行通讯";
    private final static String FUNCTION_COMM = "网络通讯功能";
    private final static String COMM_UP_SN = "上传sn，取得授权";
    private final static String COMM_TRANS = "交易通讯";
    private final static String FUNCTION_STUDY = "学习功能";
    private final static String STUDY_TRANSMIT_ACTIVITY_VALUE = "activity的传值";
    private final static String STUDY_VIEW_CREATE = "创建控件view和布局ViewGroup";
    private final static String STUDY_VIEW_CUSTOM = "自定义view";
    private final static String STUDY_VIEW_LISTVIEW = "基础ListView";
    private final static String STUDY_VIEW_LEFT_SLIDE_LISTVIEW = "可以左滑的listview";
    private final static String STUDY_VIEW_PROGRESSBAR = "进度条";
    private final static String STUDY_VIEW_DIALOG = "对话框";
    private final static String STUDY_VIEW_POPUPWINDOW = "POPUPWINDOW";
    private final static String FUNCTION_ALGORITHM = "算法功能";
    private final static String ALGORITHM_RSA = "RSA算法";
    private final static String ALGORITHM_SM2 = "sm2对称加密";
    private final static String FUNCTION_NICE_VIEW = "好看界面功能";
    private final static String NICE_VIEW_LOGIN = "登录界面";
    private final static String NICE_VIEW_MENU = "菜单界面";
    private final static String NICE_VIEW_INPUT_AMOUNT = "输入金额界面";
    private final static String FUNCTION_COMPREHENSIVE = "综合功能";
    private final static String COMPREHENSIVE_UP_LOG = "日志上传";
    private final static String COMPREHENSIVE_ALGORITHM_CALC_PWD = "保险箱计算密码";

    //待定
    private final static String FUNCTIO_UNNDETERMINED = "待定";
    @SuppressWarnings("unchecked")
    public final static HashMap<String, String> FUNCTION_MAP = new HashMap() {{
        put(SCAN_IDCARD, "com.example.function.scan.idcard.one.IdCardMainActivity");
        put(ALGORITHM_SM2, "com.example.function.algorithm.SM2_Activity");
        put(COMM_TRANS, "com.example.function.comm.PayActivity");
        put(COMPREHENSIVE_UP_LOG, "com.example.function.comprehensive.upLogActivity");
        put(NICE_VIEW_MENU, "com.example.function.View.Menu.View_Menu_Activity");
        put(NICE_VIEW_LOGIN, "com.example.function.View.Login.View_Login_Activity");
        put(NICE_VIEW_INPUT_AMOUNT, "com.example.function.View.Amount.View_InputAmount_Activity");
        put(ALGORITHM_RSA, "com.example.function.algorithm.RSA_Activity");
        put(COMPREHENSIVE_ALGORITHM_CALC_PWD, "com.example.function.comprehensive.CalcPWD_Activity");
        put(STUDY_VIEW_CUSTOM, "com.example.function.study.day6.Day6_Activity");
        put(STUDY_VIEW_CREATE, "com.example.function.study.day5.Day5_Activity");
        put(STUDY_VIEW_LISTVIEW, "com.example.function.study.day7.Day7_ArrayAdapter_listView_Activity");
        put(STUDY_VIEW_LEFT_SLIDE_LISTVIEW, "com.example.function.study.day7.Day7_LeftSlide_listView_Activity");
        put(STUDY_VIEW_PROGRESSBAR, "com.example.function.study.day8.Day8_Activity");
        put(STUDY_VIEW_DIALOG, "com.example.function.study.day9.Day9_Activity");
        put(STUDY_VIEW_POPUPWINDOW, "com.example.function.study.day10.Day10_Activity");
        put(COMM_UP_SN, "com.example.function.comm.FilesOrParamOrStringUpActivity");
        put(STUDY_TRANSMIT_ACTIVITY_VALUE, "com.example.function.study.day4.A_activity");
        put(JNI_TEST, "com.example.function.jni.jniTest.JniTestActivity");
        put(FUNCTIO_UNNDETERMINED, "com.example.function.UndeterminedActivity");
    }};

    public static final String[][] CHILD_NAMES = new String[][]{
            new String[]{SCAN_IDCARD,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED},
            new String[]{JNI_TEST,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED},
            new String[]{CALL_OTHER_APP_CCB, CALL_OTHER_APP_CGB, CALL_OTHER_APP_APPT,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED},
            new String[]{USB_HOST_QR55,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED},
            new String[]{COMM_UP_SN, COMM_TRANS,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED},
            new String[]{STUDY_TRANSMIT_ACTIVITY_VALUE, STUDY_VIEW_CREATE,
                    STUDY_VIEW_CUSTOM, STUDY_VIEW_LISTVIEW, STUDY_VIEW_LEFT_SLIDE_LISTVIEW,
                    STUDY_VIEW_PROGRESSBAR, STUDY_VIEW_DIALOG, STUDY_VIEW_POPUPWINDOW,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED},
            new String[]{ALGORITHM_RSA, ALGORITHM_SM2,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED},
            new String[]{NICE_VIEW_LOGIN, NICE_VIEW_MENU, NICE_VIEW_INPUT_AMOUNT, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED},
            new String[]{COMPREHENSIVE_UP_LOG, COMPREHENSIVE_ALGORITHM_CALC_PWD,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED},

            new String[]{FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED},
            new String[]{FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED,
                    FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED},
    };
    public static final String[] GROUP_NAMES = new String[]{FUNCTION_SCAN, FUNCTION_JNI,
            FUNCTION_CALL_OTHER_APP, FUNCTION_USB, FUNCTION_COMM, FUNCTION_STUDY, FUNCTION_ALGORITHM, FUNCTION_NICE_VIEW, FUNCTION_COMPREHENSIVE,
            FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED, FUNCTIO_UNNDETERMINED};
    public static final int[] IMAGES = new int[]{R.drawable.ic_category_0,
            R.drawable.ic_category_10, R.drawable.ic_category_30, R.drawable.ic_category_20
            , R.drawable.ic_category_60, R.drawable.ic_category_50, R.drawable.ic_category_45,
            R.drawable.ic_category_50, R.drawable.ic_category_70,
            R.drawable.ic_category_65, R.drawable.ic_category_80, R.drawable.ic_category_80,
            R.drawable.ic_category_70,
            R.drawable.ic_category_65, R.drawable.ic_category_80, R.drawable.ic_category_80};

}

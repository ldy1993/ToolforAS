package com.KeyTool;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/9/6
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class KEY {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String getMchPrivateKey();
    public static native String getPlatPublicKey();

}

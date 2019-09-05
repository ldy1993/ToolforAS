package com.ldy.function.sign.config;

import android.util.Log;

import com.ldy.function.Network.CommManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * <一句话功能简述>
 * <功能详细描述>配置信息
 *  不用这里
 * @author  Administrator
 * @version  [版本号, 2014-8-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SwiftpassConfig {
    private static final String TAG = "SwiftpassConfig";
    /**
     * 交易密钥
     */
    public static String key;
    
    public static String mchPrivateKey;
    
    public static String platPublicKey;

    static{
        Properties prop = new Properties();   
//        InputStream in = SwiftpassConfig.class.getResourceAsStream("/config.properties");

        try {
            InputStream in = CommManager.context.getAssets().open("config.properties");
            prop.load(in);   
            key = prop.getProperty("key").trim();
            mchPrivateKey = prop.getProperty("mchPrivateKey").trim();
            platPublicKey = prop.getProperty("platPublicKey").trim();
            Log.e(TAG, "mchPrivateKey: "+mchPrivateKey);
            Log.e(TAG, "platPublicKey: "+platPublicKey );
        } catch (IOException e) {   
            e.printStackTrace();   
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

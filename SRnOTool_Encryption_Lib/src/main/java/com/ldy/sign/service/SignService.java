package com.ldy.sign.service;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/9/5
 * 描    述：网络通讯接口，分别是
 * 1、post 键值对参数或文件
 * 2、post String
 * 修订历史：
 * ================================================
 */
public interface SignService {
     String getSign(String signType,String preStr);
     boolean verifySign(String preStr,String sign,String signType ) throws Exception;
}
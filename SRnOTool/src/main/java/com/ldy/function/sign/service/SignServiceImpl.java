package com.ldy.function.sign.service;

import com.ldy.function.sign.Instantiation.SignUtil;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/9/5
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class SignServiceImpl implements SignService {
    @Override
    public String getSign(String signType, String preStr) {
        return (String) SignUtil.getSign("RSA_1_256", preStr);

    }

    @Override
    public boolean verifySign(String preStr, String sign, String signType) throws Exception {
        return (boolean) SignUtil.getVerifySign(preStr,sign,"RSA_1_256");
    }
}

package com.fhypayaso.orochi.utils;

import org.jasypt.util.text.BasicTextEncryptor;

public class JasyptUtil {
    /**
     * 加密方法
     *
     * @param salt         盐值
     * @param targetString 待加密字符串
     * @return 密文
     */
    public static String encrypt(String salt, String targetString) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(salt);
        return encryptor.encrypt(targetString);
    }

    /**
     * 解密方法
     *
     * @param salt         盐值
     * @param targetString 待解密字符串
     * @return 明文
     */
    public static String decrypt(String salt, String targetString) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(salt);
        return encryptor.decrypt(targetString);
    }
}

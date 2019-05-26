package com.fhypayaso.orochi.utils;


import java.math.BigInteger;
import java.security.MessageDigest;

public final class EncryptUtil {

    private EncryptUtil() {
    }

    private static final String SALT = "aafje9890312hkaf";

    public static String encrypt(String secret) throws Exception {
        secret = secret + SALT;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(secret.getBytes());
        return new BigInteger(1, md.digest()).toString();
    }

    public static boolean check(String raw, String old) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        raw = raw + SALT;
        md.update(raw.getBytes());
        return new BigInteger(1, md.digest()).toString().equals(old);
    }
}

package com.proky.booking.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncryptor {
    private static PasswordEncryptor mInstance;

    private PasswordEncryptor() {
    }

    public static PasswordEncryptor getInstance() {
        if (mInstance == null) {
            mInstance = new PasswordEncryptor();
        }
        return mInstance;
    }

    public String encrypt(String password) {
        return DigestUtils.sha256Hex(password);
    }
}

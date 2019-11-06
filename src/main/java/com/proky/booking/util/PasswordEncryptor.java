package com.proky.booking.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncryptor {
    public String encryptPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }
}

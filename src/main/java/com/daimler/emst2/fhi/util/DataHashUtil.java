package com.daimler.emst2.fhi.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.daimler.emst2.frw.webexceptions.NotAcceptableRuntimeException;

public class DataHashUtil {
    private DataHashUtil() {
    }

    public static final String createHash() {
        return createHash(new Date());
    }

    public static final String createHash(Date datum) {
        if (datum == null) {
            return "NULL-NO-HASH";
        }
        return createHash(Long.toString(datum.getTime()));
    }

    public static final String createHash(String text) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new NotAcceptableRuntimeException("MD5 Hash not available on REST-Server side.");
        }
        byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.ISO_8859_1));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

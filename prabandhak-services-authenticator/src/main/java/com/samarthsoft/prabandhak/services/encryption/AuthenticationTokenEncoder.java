package com.samarthsoft.prabandhak.services.encryption;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class AuthenticationTokenEncoder {

    private static final int                  ITERATION_COUNT = 5;

    public AuthenticationTokenEncoder() {
    }

    public synchronized String encode(String inputString, String saltKey)
            throws NoSuchAlgorithmException, IOException {
        String encodedInputString = null;
        byte[] salt = base64ToByte(saltKey);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);

        byte[] inputStringBytes = digest.digest(inputString.getBytes("UTF-8"));
        for (int i = 0; i < ITERATION_COUNT; i++) {
            digest.reset();
            inputStringBytes = digest.digest(inputStringBytes);
        }
        encodedInputString = byteToBase64(inputStringBytes);
        return encodedInputString;
    }

    private byte[] base64ToByte(String str) throws IOException {
        Base64 decoder = new Base64();
        return decoder.decode(str.getBytes());
    }

    private String byteToBase64(byte[] bt) {
        Base64 endecoder = new Base64();
        //String returnString = endecoder.encode(bt);
        //return returnString;

        return new String(endecoder.encode(bt));
    }
}
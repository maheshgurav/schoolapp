package com.samarthsoft.prabandhak.services.encryption;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public final class EncryptionUtils {
    public static String generateSha256Hash(String inputString, String saltKey) throws NoSuchAlgorithmException, IOException {
        String authenticationToken = "";
        AuthenticationTokenEncoder encoder = new AuthenticationTokenEncoder();
        authenticationToken = encoder.encode(inputString, saltKey);
        return authenticationToken;
    }

    public static boolean isHashSame(String hash1, String hash2)
    {
        //some commons-codec version introduce unnecessary new line at end of hash, following code will handle it
        if (hash1.length() != hash2.length()) {
            int minLength = (hash1.length() < hash2.length() ? hash1.length() : hash2.length());
            hash1 = hash1.substring(0, minLength);
            hash2 = hash2.substring(0, minLength);
        }
        return hash1.equals(hash2);
    }
}

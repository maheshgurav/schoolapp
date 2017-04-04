package com.samarthsoft.prabandhak.services.encryption;

public class DefaultTokenHandler {
    private static final String hashSaltKey = "hytvvd3Ex_cf356FtrcgF5$3@4578$fFRCgtdc436CVtdvgf$#fdvggfdecFrc5$3f";

    public String generateHash(String inputString) throws Exception {
        return EncryptionUtils.generateSha256Hash(inputString, hashSaltKey);
    }

}

package com.shcem.Encrypt;

import java.security.Key;

/**
 * Created by lizhihua on 2017/2/23.
 */
public class TripleDESHelper extends DESEncryptHelper {
    private static final TripleDESHelper helper=new TripleDESHelper();
    public static TripleDESHelper Instance(){
        return helper;
    }

    protected final String ALGORITHM_DES = "DESede";

    protected TripleDESHelper() {
        super("DES");
    }

    @Override
    protected Key toKey(byte[] key) throws Exception {
        return super.toKey(key);
    }

    @Override
    public String decryptString(String data, String key) throws Exception {
        return super.decryptString(data, key);
    }

    @Override
    public String encryptString(String data, String key) throws Exception {
        return super.encryptString(data, key);
    }
}

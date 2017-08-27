package com.shcem.Encrypt;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by lizhihua on 2017/2/23.
 */
public class AESHelper extends DesEncryptBase {
    private static final AESHelper helper=new AESHelper("AES");
    public static AESHelper Instance(){
        return helper;
    }
    protected AESHelper(String algorithm)
    {
        super(algorithm);
        //ALGORITHM_DES=algorithm;
    }
    protected String ALGORITHM_DES = "AES";
    /**
     * 转换密钥<br>
     *
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    protected Key toKey(byte[] key) throws Exception {

        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM_DES);

        return secretKey;
    }
}

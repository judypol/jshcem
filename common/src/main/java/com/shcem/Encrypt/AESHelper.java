package com.shcem.Encrypt;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by lizhihua on 2017/2/23.
 */
public class AESHelper extends DesEncryptBase {
    private static final AESHelper helper = new AESHelper("AES");

    public static AESHelper Instance() {
        return helper;
    }

    protected AESHelper(String algorithm) {
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

        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        kg = KeyGenerator.getInstance(ALGORITHM_DES);

        SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(key);

        //AES 要求密钥长度为 128
        kg.init(128, secureRandom);


        //生成一个密钥
        SecretKey secretKey = kg.generateKey();

        return secretKey;// 转换为AES专用密钥

    }
}

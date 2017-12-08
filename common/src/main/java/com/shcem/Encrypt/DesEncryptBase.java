package com.shcem.Encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

import static com.shcem.Encrypt.EncrytHelper.decryptBASE64;
import static com.shcem.Encrypt.EncrytHelper.encryptBASE64;

/**
 * Created by lizhihua on 2017/2/23.
 */
public abstract class DesEncryptBase {
    protected DesEncryptBase(String algorithm)
    {
        ALGORITHM_DES=algorithm;
    }
    /**
     * ALGORITHM 算法 <br>
     * 可替换为以下任意一种算法，同时key值的size相应改变。
     *
     * <pre>
     * DES          		key size must be equal to 56
     * DESede(TripleDES) 	key size must be equal to 112 or 168
     * AES          		key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
     * Blowfish     		key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
     * RC2          		key size must be between 40 and 1024 bits
     * RC4(ARCFOUR) 		key size must be between 40 and 1024 bits
     * </pre>
     *
     * 在Key toKey(byte[] key)方法中使用下述代码
     * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code> 替换
     * <code>
     * DESKeySpec dks = new DESKeySpec(key);
     * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
     * SecretKey secretKey = keyFactory.generateSecret(dks);
     * </code>
     */
    protected String ALGORITHM_DES;
    /**
     * 转换密钥<br>
     *
     * @param key
     * @return
     * @throws Exception
     */
    protected abstract Key toKey(byte[] key) throws Exception;
//    {
//        DESKeySpec dks = new DESKeySpec(key);
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
//        SecretKey secretKey = keyFactory.generateSecret(dks);
//
//        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
//        // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
//
//        return secretKey;
//    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);

        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.DECRYPT_MODE, k);

        return cipher.doFinal(data);
    }
    public String decryptString(String data,String key) throws Exception{
        byte[] initKey=initKey(key);
        byte[] source=decryptBASE64(data);
        byte[] decrypt=decrypt(source,initKey);

        return new String(decrypt,"UTF-8");
    }
    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.ENCRYPT_MODE, k);

        return cipher.doFinal(data);
    }
    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public String encryptString(String data,String key) throws Exception{
        byte[] initKey=initKey(key);
        byte[] source=data.getBytes();
        byte[] encrypt= encrypt(source,initKey);

        return encryptBASE64(encrypt);
    }

    /**
     * 生成密钥
     *
     * @param seed
     * @return
     * @throws Exception
     */
    private byte[] initKey(String seed) throws Exception {
        byte[] keyBytes=seed.getBytes();

        SecretKey key3=new SecretKeySpec(keyBytes, ALGORITHM_DES);
        return key3.getEncoded();
    }
}

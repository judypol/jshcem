package com.shcem.Encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * Created by lizhihua on 2017/2/20.
 *
 */
public abstract class EncrytHelper {
    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";

    /**
     * MAC算法可选以下多种算法
     *
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacSHA256";

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new Base64()).decode(key);
    }
    /**
     * Base64解密
     * @param key--输入的带加密字符串
     * **/
    public static String decryptBase64(String key) throws Exception{
        byte[] decrypt=decryptBASE64(key);
        return new String(decrypt,"UTF-8");
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        byte[] encrypt= (new Base64()).encode(key);
        return new String(encrypt,"UTF-8");
    }
    /**
     * Base64加密
     * */
    public static String encryptBase64(String key) throws Exception{
        byte[] source=key.getBytes("UTF-8");
        return encryptBASE64(source);
    }
    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);

        return md5.digest();
    }
    /**
     * MD5加密
     * */
    public static String encryptMD5(String data) throws Exception{
        MessageDigest md5=MessageDigest.getInstance(KEY_MD5);
        byte[] source=data.getBytes("UTF-8");
        md5.update(source);
        byte[] encrypt=md5.digest();

        return encryptBASE64(encrypt);
    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }
    public static String encryptSHA(String data) throws Exception{
        byte[] source=data.getBytes("UTF-8");

        byte[] encrypt=encryptSHA(source);
        return new String(encrypt,"UTF-8");
    }

    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }
}

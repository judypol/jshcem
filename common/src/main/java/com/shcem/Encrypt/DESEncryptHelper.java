package com.shcem.Encrypt;

/**
 * Created by judysen on 2017/2/19.
 */

import com.shcem.utils.ExceptionUtils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;


/**
 * DES安全编码组件
 * <p>
 * <pre>
 * 支持 DES、DESede(TripleDES,就是3DES)、AES、Blowfish、RC2、RC4(ARCFOUR)
 * DES          		key size must be equal to 56
 * DESede(TripleDES) 	key size must be equal to 112 or 168
 * AES          		key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
 * Blowfish     		key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
 * RC2          		key size must be between 40 and 1024 bits
 * RC4(ARCFOUR) 		key size must be between 40 and 1024 bits
 * 具体内容 需要关注 JDK Document http://.../docs/technotes/guides/security/SunProviders.html
 * </pre>
 */
public class DESEncryptHelper {
    private static final DESEncryptHelper helper = new DESEncryptHelper();

    private final String ALGORITHM="DES";
    public static DESEncryptHelper Instance() {
        return helper;
    }

    protected DESEncryptHelper() {

        //ALGORITHM_DES=algorithm;
    }

    public byte[] decrypt(byte[] datasource, String key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(datasource);
    }

    /**
     * 加密字符串必须为base64
     *des解密，返回utf-8字符串
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public String decryptString(String data, String key) throws Exception {
        byte[] source=Base64.decodeBase64(data);
        byte[] decryptBytes=this.decrypt(source,key);
        return new String(decryptBytes,"UTF-8");
    }

    /**
     * 获取加密后的UTF-8编码字符串
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public String encryptString(String data, String key) throws Exception {
        byte[] source=data.getBytes("UTF-8");
        byte[] encryptBytes=this.encrypt(source,key);
        return Base64.encodeBase64String(encryptBytes);
    }
    public byte[] encrypt(byte[] source,String key) throws Exception{
        SecureRandom random = new SecureRandom();
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
        return cipher.doFinal(source);
    }
}



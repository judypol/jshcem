package com.shcem.Encrypt;

/**
 * Created by judysen on 2017/2/19.
 */

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;


/**
 * DES安全编码组件
 *
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
 *
 */
public class DESEncryptHelper extends DesEncryptBase{
    private static final DESEncryptHelper helper=new DESEncryptHelper("DES");
    public static DESEncryptHelper Instance(){
        return helper;
    }
    protected DESEncryptHelper(String algorithm){
        super(algorithm);
        //ALGORITHM_DES=algorithm;
    }
    protected String ALGORITHM_DES="DES";
    @Override
    protected Key toKey(byte[] key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);

        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
        // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);

        return secretKey;
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



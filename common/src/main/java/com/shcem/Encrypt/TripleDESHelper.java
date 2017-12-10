package com.shcem.Encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;

/**
 * Created by lizhihua on 2017/2/23.
 */
public class TripleDESHelper{
    private static final TripleDESHelper helper=new TripleDESHelper();
    public static TripleDESHelper Instance(){
        return helper;
    }

    protected final String ALGORITHM_DES = "DESede";

    protected TripleDESHelper() {

    }

    public byte[] decrypt(byte[] source,String key) throws Exception{
        SecretKey deskey=new SecretKeySpec(build3DesKey(key),ALGORITHM_DES);
        Cipher cipher=Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.DECRYPT_MODE,deskey);

        byte[] desBytes=cipher.doFinal(source);
        return desBytes;
    }

    /**
     * 解密字符串
     * 加密字符串必须是Base64编码过
     * * 返回UTF-8字符
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public String decryptString(String data, String key) throws Exception {
        byte[] desBytes=this.decrypt(Base64.decodeBase64(data),key);

        return new String(desBytes,"UTF-8");
    }

    public byte[] encrypt(byte[] source,String key) throws Exception{
        SecretKey deskey=new SecretKeySpec(build3DesKey(key),"DESede");
        Cipher c1=Cipher.getInstance(ALGORITHM_DES);

        c1.init(Cipher.ENCRYPT_MODE,deskey);
        byte[] desBytes= c1.doFinal(source);

        return desBytes;
    }

    /**
     * 加密字符串
     * 返回base64编码的字符串
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public String encryptString(String data, String key) throws Exception {
        byte[] source=data.getBytes("UTF-8");
        byte[] bytes=this.encrypt(source,key);

        return Base64.encodeBase64String(bytes);
    }
    public byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException{
        byte[] key=new byte[24];
        byte[] temp=Base64.decodeBase64(keyStr);
        if(key.length>temp.length){
            System.arraycopy(temp,0,key,0,temp.length);
        }else{
            System.arraycopy(temp,0,key,0,key.length);
        }
        return key;
    }
}

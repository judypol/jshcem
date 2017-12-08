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

    protected final String ALGORITHM_DES = "DESede/CBC/PKCS5Padding";

    protected TripleDESHelper() {

    }


    public String decryptString(String data, String key) throws Exception {
        SecretKey deskey=new SecretKeySpec(build3DesKey(key),ALGORITHM_DES);
        Cipher cipher=Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.DECRYPT_MODE,deskey);

        byte[] src=data.getBytes("UTF-8");
        byte[] desBytes=cipher.doFinal(src);

        return Base64.encodeBase64String(desBytes);
    }

    public String encryptString(String data, String key) throws Exception {
        SecretKey deskey=new SecretKeySpec(build3DesKey(key),"DESede");
        Cipher c1=Cipher.getInstance(ALGORITHM_DES);
        IvParameterSpec iv = new IvParameterSpec(Base64.decodeBase64("QgAZ/DV7dpA="));

        c1.init(Cipher.ENCRYPT_MODE,deskey,iv);
        byte[] src=data.getBytes("UTF-16LE");
        byte[] desBytes= c1.doFinal(src);

        return EncrytHelper.encryptBASE64(desBytes);
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

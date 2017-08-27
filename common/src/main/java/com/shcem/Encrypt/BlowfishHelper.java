package com.shcem.Encrypt;

/**
 * Created by lizhihua on 2017/2/23.
 */
public class BlowfishHelper extends AESHelper {
    private static BlowfishHelper helper=new BlowfishHelper();

    public static BlowfishHelper Instance(){
        return helper;
    }

    protected BlowfishHelper()
    {
        super("Blowfish");
    }
}

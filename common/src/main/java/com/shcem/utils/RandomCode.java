package com.shcem.utils;

import java.util.Random;

/**
 * Created by lizhihua on 2017/4/11.
 */
public class RandomCode {
    // 可选字符
    private String codes  = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String numCodes="0123456789";
    private Random r = new Random();
    /**返回一个随机字符
     * */
    private char randomChar()
    {
        int index=r.nextInt(codes.length());
        return codes.charAt(index);
    }
    private char randomNumber(){
        int index=r.nextInt(numCodes.length());
        return numCodes.charAt(index);
    }
    /**返回指定的字符长度
     * @param length,指定的随机码的长度
     * */
    public String RandomCoder(int length){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            char c=randomChar();
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 返回指定长度的由数字组成的字符串，
     * @param length
     * @return
     */
    public String randomNumber(int length){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            char c=randomNumber();
            sb.append(c);
        }
        return sb.toString();
    }

    public static RandomCode Instance(){
        RandomCode code=new RandomCode();
        return code;
    }
}

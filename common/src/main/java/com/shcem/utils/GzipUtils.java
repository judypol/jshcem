/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/1 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author lizhihua
 * @version 1.0
 */
public class GzipUtils {
    private static Logger logger= LoggerFactory.getLogger(GzipUtils.class);

    /**
     * gizp压缩，指定编码
     * @param str
     * @param encoding
     * @return
     */
    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch (IOException e) {
            logger.error("gzip compress error.", e);
        }
        return out.toByteArray();
    }

    /**
     * 将字符串通过gzip压缩成字符串
     * @param str
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String compress2String(String str,String encoding) throws UnsupportedEncodingException{
        byte[] bytes=compress(str,encoding);
        return new String(bytes,"ISO-8859-1");
    }

    /**
     * 默认通过UTF-8压缩
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String compress2String(String str) throws UnsupportedEncodingException{
        return compress2String(str,"UTF-8");
    }

    /**
     * gzip解压
     * @param bytes
     * @return
     */
    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            logger.error("gzip uncompress error.", e);
        }

        return out.toByteArray();
    }

    /**
     * gzip解压，指定编码方式
     * @param compressStr
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String uncompress2String(String compressStr,String encoding) throws UnsupportedEncodingException{
        byte[] bytes=compressStr.getBytes("ISO-8859-1");

        byte[] unBytes=uncompress(bytes);
        return new String(unBytes,encoding);
    }

    /**
     * gzip解压，UTF-8
     * @param compressStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String uncompress2String(String compressStr) throws UnsupportedEncodingException{
        return uncompress2String(compressStr,"UTF-8");
    }
}

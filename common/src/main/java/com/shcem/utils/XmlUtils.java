package com.shcem.utils;

import com.shcem.Encrypt.EncrytHelper;
import com.shcem.mapper.JaxbMapper;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/11/26 0026.
 */
public class XmlUtils {
    private static ConcurrentHashMap<String,XmlEntity> map=new ConcurrentHashMap<>();

    public synchronized static <T> T xml2Entity(File file,Class<T> cls) throws Exception{
//        if(filename.startsWith("classpath:")){
//            file= ResourceUtils.getFile(filename);
//        }else{

        if(!file.exists()){
            throw new Exception("can not find the file:"+file.getName());
        }
        long modifiedDate=getFileMobidyDate(file);

        XmlEntity mapO=map.get(file.getPath());
        if(mapO==null||mapO.getModifyDate()<modifiedDate){
            BOMInputStream bomInputStream=new BOMInputStream(new FileInputStream(file));
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(bomInputStream));

            StringBuilder sb=new StringBuilder();
            bufferedReader.lines().forEach(l->sb.append(l));

            String xml=sb.toString();

            mapO=new XmlEntity();
            mapO.setModifyDate(modifiedDate);
            mapO.setObj(JaxbMapper.fromXml(xml,cls));

            map.put(file.getPath(),mapO);
        }

        return (T)mapO.getObj();
    }

    /**
     *
     * @param contents
     * @param cls
     * @param <T>
     * @return
     */
    public synchronized static <T> T xml2Entity(String contents,Class<T> cls) throws Exception{
        XmlEntity mapO=map.get(cls.getName());
        String md5= EncrytHelper.encryptMD5(contents);
        if(mapO==null||!md5.equals(mapO.getMd5())){

            String xml=contents;

            mapO=new XmlEntity();
            mapO.setObj(JaxbMapper.fromXml(xml,cls));
            mapO.setMd5(md5);
            map.put(cls.getName(),mapO);
        }

        return (T)mapO.getObj();
    }

    /**
     *
     * @param file
     * @return
     */
    private static long getFileMobidyDate(File file){
        long modified=file.lastModified();
        return modified;
    }
}

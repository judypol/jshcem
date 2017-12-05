package com.shcem.utils;

import com.shcem.mapper.JaxbMapper;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/11/26 0026.
 */
public class XmlUtils {
    private static ConcurrentHashMap<String,XmlEntity> map=new ConcurrentHashMap<>();

    public synchronized static <T> T xml2Entity(String filename,Class<T> cls) throws Exception{
        File file=null;
        if(filename.startsWith("classpath:")){
            file= ResourceUtils.getFile(filename);
        }else{
            file=new File(filename);
            if(!file.exists()){
                throw new Exception("can not find the file:"+filename);
            }
        }
        long modifiedDate=getFileMobidyDate(file);

        XmlEntity mapO=map.get(filename);
        if(mapO==null||mapO.getModifyDate()<modifiedDate){
            BOMInputStream bomInputStream=new BOMInputStream(new FileInputStream(file));
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(bomInputStream));

            StringBuilder sb=new StringBuilder();
            bufferedReader.lines().forEach(l->sb.append(l));

            String xml=sb.toString();
            //String xml=FileUtils.readFileToString(file).replace("\r\n","").replace("\\s+","");

            mapO=new XmlEntity();
            mapO.setModifyDate(modifiedDate);
            mapO.setObj(JaxbMapper.fromXml(xml,cls));

            map.put(filename,mapO);
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

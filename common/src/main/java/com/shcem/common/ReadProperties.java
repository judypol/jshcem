package com.shcem.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.*;

/**
 *
 * Created by judysen on 2017/2/25.
 */
public class ReadProperties {
    private ReadProperties() {

    }

    private static PropertiesLoader loader = null;
    private static Date modifyDate=new Date();

    public static synchronized PropertiesLoader AppConfig() {
        String otherconfig = "";
        if (OSCheck.isWindows()) {
            otherconfig = "file:/C:/mltp/mltp.config";
        } else {
            otherconfig = "file:/etc/mltp/mltp.config";
        }
        Date tModifyDate = getFileDate(otherconfig);
        boolean flag= modifyDate.before(tModifyDate);
        if (loader == null || modifyDate.before(tModifyDate)) {
            Logger logger = LoggerFactory.getLogger(ReadProperties.class);
            String appconfig="classpath:config/app.config";
            String path="classpath:app.config";

            loader = new PropertiesLoader(appconfig, path, otherconfig);
            modifyDate=tModifyDate;
        }
        return loader;
    }

    private static Date getFileDate(String filename) {
        File f = new File(filename);
        if (!f.exists())
            System.out.println("AppConfiguration--getFileDate--con not found the file");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(f.lastModified());
        return cal.getTime();
    }
}

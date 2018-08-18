package com.shcem.common.fadada.utils;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Property Util
 *
 * @author chiyong
 * @version 1.0
 */
public class PropertyUtil {

    private static PropertyUtil pro = null;

    /**
     *
     * yujp
     * @return
     */
    public static synchronized PropertyUtil getInstance() {
        if (pro == null) {
            pro = new PropertyUtil();
        }
        return pro;
    }

    /**
     * java.uti.Properties
     *
     * @param file
     *            is the absolute path of the property
     * @return
     */
    public Properties propertiesUtil(String file) {
        Properties prop = null;
        try {
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            prop = new Properties();
            prop.load(is);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * java.util.ResourceBundle
     *
     * @param file
     *            is the absolute path of the property
     * @return
     */
    public ResourceBundle resourceBundle(String file) {
        ResourceBundle res = ResourceBundle.getBundle(file, Locale.getDefault());
        return res;
    }

    /**
     * java.util.PropertyResourceBundle
     *
     * @param file
     *            is the absolute path of the property
     * @return
     */
    public PropertyResourceBundle propResourceBundle(String file) {
        BufferedInputStream bis;
        PropertyResourceBundle prs = null;
        try {
            // String path = System.getProperty("user.dir");
            bis = new BufferedInputStream(new FileInputStream(file));
            prs = new PropertyResourceBundle(bis);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prs;
    }

    /**
     * java.uti.Properties
     *
     * @param file
     *            is the relative path of the property
     * @return
     */
    public Properties getProperties(String file) {
        Properties props = new Properties();
        InputStream in;
        try {
//            ClassLoader.getSystemResourceAsStream(file);
            in = getClass().getResourceAsStream(file);
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }
}

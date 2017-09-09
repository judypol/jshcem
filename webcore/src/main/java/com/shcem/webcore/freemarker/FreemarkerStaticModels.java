package com.shcem.webcore.freemarker;

import com.shcem.common.PropertyUtil;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.ext.beans.BeansWrapperConfiguration;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by judysen on 2017/8/27.
 */
public class FreemarkerStaticModels extends HashMap<String,Object> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static FreemarkerStaticModels FREEMARKER_STATIC_MODELS;
    private static String freemakerStaticPropertiesFile="classpath:freemarkerStaticModel.properties";
    private static Properties staticModels;

    private FreemarkerStaticModels(){

    }
    //@Autowired(required = false)
    //private IStaticResource staticResource;
    public static FreemarkerStaticModels getInstance(){
        if(FREEMARKER_STATIC_MODELS==null){
            FREEMARKER_STATIC_MODELS=new FreemarkerStaticModels();
        }
        staticModels= PropertyUtil.create(freemakerStaticPropertiesFile).getProperties();
        Set<String> keys=staticModels.stringPropertyNames();
        for (String key : keys) {
            FREEMARKER_STATIC_MODELS.put(key, useStaticPackage(staticModels.getProperty(key)));
        }
        return FREEMARKER_STATIC_MODELS;
    }

    public static TemplateHashModel useStaticPackage(String packageName){
        try
        {
            BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_23).build();
            TemplateHashModel staticModels = wrapper.getStaticModels();
            TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get(packageName);
            return fileStatics;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

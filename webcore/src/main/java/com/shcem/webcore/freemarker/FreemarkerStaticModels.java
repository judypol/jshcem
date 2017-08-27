package com.shcem.webcore.freemarker;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.ext.beans.BeansWrapperConfiguration;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;

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
    private Properties staticModels;

    private FreemarkerStaticModels(){

    }

    public static FreemarkerStaticModels getInstance(){
        if(FREEMARKER_STATIC_MODELS==null){
            FREEMARKER_STATIC_MODELS=new FreemarkerStaticModels();
        }
        return FREEMARKER_STATIC_MODELS;
    }

    public Properties getStaticModels() {
        return staticModels;
    }

    public void setStaticModels(Properties staticModels) {
        if(this.staticModels==null&&staticModels!=null){
            this.staticModels = staticModels;
            Set<String> keys=this.staticModels.stringPropertyNames();
            for (String key : keys) {
                FREEMARKER_STATIC_MODELS.put(key, useStaticPackage(this.staticModels.getProperty(key)));
            }
        }
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

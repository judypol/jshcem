package com.shcem.webcore.freemarker;

import com.shcem.utils.ObjectUtils;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.util.HashMap;
import java.util.Map;

public class FreemarkerTemplateModel implements TemplateHashModel {
    BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_23).build();
    HashMap<String,TemplateModel> map=new HashMap<>();
    public FreemarkerTemplateModel(Object obj){
        getInstance(obj);
    }
    /**
     *
     * @param obj
     * @return
     */
    private void getInstance(Object obj){
        Map<String,Object> bean2Map= ObjectUtils.transBean2Map(obj);
        for(String key:bean2Map.keySet()){
            try{
                this.map.put(key,wrapper.wrap(bean2Map.get(key)));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public TemplateModel get(String s) throws TemplateModelException {
        return this.map.get(s);
    }

    @Override
    public boolean isEmpty() throws TemplateModelException {
        return false;
    }
}

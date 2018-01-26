package com.shcem.webcore.freemarker;

import com.shcem.utils.Reflections;
import com.shcem.utils.SpringContextHolder;
import freemarker.cache.TemplateLoader;
import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 使用方法：
 *      <@partialView controller="com.example.demo.controller.HomeController" view="parialView" params="">
 *      </@partialView>
 * 注意：1、对应的方法在class下只能有一个，不能有重载方法
 *      2、返回的类型必须是ModelAndView类型
 *      3、参数必须是一个并且是String类型，其他类型不支持
 * 局部视图
 */
public class PartialView implements TemplateDirectiveModel {
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels,
                        TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        if(map.get("controller")==null){
            throw new TemplateException("参数controller is null",environment);
        }
        if(map.get("view")==null){
            throw new TemplateException("参数view is null",environment);
        }

        String controller=map.get("controller").toString();
        String view=map.get("view").toString();

        Object params=map.get("params");
        if(params!=null){
            params=params.toString();
        }
        try{
            //Class cls=Class.forName(controller);
            Object obj=SpringContextHolder.GetBean(controller);
            Method method =Reflections.GetAccessibleMethodByName(obj,view);
            Object result=null;
            if(method.getParameterCount()==0){
                result=method.invoke(obj,null);
            }else if(method.getParameterCount()==1){
                result=method.invoke(obj,params);
            }else{
                throw new TemplateException("partialView 对应的方法有误，目前只支持参数是String类型的参数，并且参数只有一位",environment);
            }
            if(result==null){
                return;
            }

            if(result instanceof ModelAndView){
                renderPartialView(environment,(ModelAndView) result);
            }else{
                throw new TemplateException("partialView 对应的方法有误，返回必须是ModelAndView类型",environment);
            }

        }catch (Exception ex){
            throw new TemplateException(ex,environment);
        }
    }
    /**
     * Description:获取ftl完整路径 <br>
     */
    private String getFullTemplatePath(Environment environment, String templatePath)
            throws MalformedTemplateNameException {

        String currentTemplateName = environment.getTemplate().getName();
        //final String baseName = FilenameUtils.getPath(currentTemplateName);
        String subfix=FilenameUtils.getExtension(currentTemplateName);
        //final String targetName =templatePath; //params.get(templatePath).toString();
        //final String fullTemplatePath = environment.toFullTemplateName(baseName, targetName);

        return templatePath+"."+subfix;
    }

    /**
     * 执行模板
     * @param environment
     * @param modelAndView
     * @throws TemplateException
     * @throws IOException
     */
    private void renderPartialView(Environment environment, ModelAndView modelAndView) throws TemplateException,IOException{
        if(modelAndView==null){
            throw new TemplateException("modelandView 不能为空！",environment);
        }
        TemplateLoader templateLoader = environment.getConfiguration().getTemplateLoader();

        String templatePath=getFullTemplatePath(environment,modelAndView.getViewName());
        if(templateLoader.findTemplateSource(templatePath)!=null){
            Map<String,Object> map=modelAndView.getModel();

            for(String key:map.keySet()){
                environment.setVariable(key,environment.getObjectWrapper().wrap(map.get(key)));
            }
            Template template=environment.getTemplateForInclusion(templatePath, null, true);
            environment.include(template);
        }else{
            throw new TemplateException("找不到对应的模板:"+templatePath,environment);
        }
    }
}

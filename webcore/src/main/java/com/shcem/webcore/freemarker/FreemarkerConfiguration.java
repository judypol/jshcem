package com.shcem.webcore.freemarker;

import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by judysen on 2017/9/9.
 */
public class FreemarkerConfiguration  {
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver(){
        FreeMarkerViewResolver resolver=new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setSuffix(".ftl");
        resolver.setRequestContextAttribute("ctx");
        if(staticResource!=null){
            TemplateHashModel hashModel =FreemarkerStaticModels.useStaticPackage(staticResource.getClass().getName());
            HashMap<String,TemplateHashModel> hashModelHashMap=new HashMap<>();
            hashModelHashMap.put("staticResource",hashModel);
            resolver.setAttributesMap(hashModelHashMap);
        }
        return resolver;
    }
    @Autowired(required = false)
    private IStaticResource staticResource;
    /**
     *
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException,TemplateException {
        FreeMarkerConfigurationFactory factory=new FreeMarkerConfigurationFactory();
        factory.setDefaultEncoding("UTF-8");
        factory.setTemplateLoaderPath("classpath:/templates");
        HashMap<String,Object> root=new HashMap<>();
        factory.setFreemarkerVariables(root);



        FreeMarkerConfigurer configurer=new FreeMarkerConfigurer();
        configurer.setConfiguration(factory.createConfiguration());

        return configurer;
    }


}

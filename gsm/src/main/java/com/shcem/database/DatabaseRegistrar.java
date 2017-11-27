/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/9/8 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.shcem.common.YamlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @author lizhihua
 * @version 1.0
 */
public class DatabaseRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    Logger logger= LoggerFactory.getLogger(DatabaseRegistrar.class);
    private ResourceLoader resourceLoader;
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader=resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        try{
            if(!this.registerBean(registry)){               //--加载app.yaml配置文件下的数据库地址
                this.registerBeanInClassPath(registry);     //--加载默认路径下的配置文件--config/datasource.xml
            }
        }catch (Exception ex){
            System.out.println();
        }
    }
    private boolean registerBean(BeanDefinitionRegistry registry){
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(registry);
        String dataSourceLocation=YamlConfiguration.instance().getString("dataSourceLocation");
        if(StringUtils.isEmpty(dataSourceLocation)){
            return false;
        }

        File dataSourceFile=new File(dataSourceLocation);
        if(!dataSourceFile.exists()){
            logger.info(dataSourceLocation+" is not found");
            return false;
        }
        try{
            Resource resource =new FileSystemResource(dataSourceFile);
            xmlBeanDefinitionReader.loadBeanDefinitions(resource);
            return true;
        }catch (Exception ex){
            throw ex;
        }
    }
    private boolean registerBeanInClassPath(BeanDefinitionRegistry registry){
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(registry);
        try{
            xmlBeanDefinitionReader.loadBeanDefinitions(this.resourceLoader.getResource("classpath:config/datasource.xml"));
            return true;
        }catch (Exception ex){
            throw ex;
        }
    }
}

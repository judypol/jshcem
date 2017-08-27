package com.shcem.hessian;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * Created by judysen on 2017/8/20.
 */
public class HessianServiceScanner implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanNamesForAnnotation(HessianService.class);
        for (String beanName : beanNames) {
            String className = beanFactory.getBeanDefinition(beanName).getBeanClassName();
            Class<?> clasz = null;
            Class<?> claszInterface=null;
            try {
                clasz = Class.forName(className);
                claszInterface=clasz.getInterfaces()[0];
            } catch (ClassNotFoundException e) {
                throw new BeanInitializationException(e.getMessage(), e);
            }
            String hessianServiceBeanName = "/" + claszInterface.getSimpleName();

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(HessianServiceExporter.class);
            builder.addPropertyReference("service", beanName);
            builder.addPropertyValue("serviceInterface", claszInterface.getName());
            ((BeanDefinitionRegistry) beanFactory).registerBeanDefinition(hessianServiceBeanName, builder.getBeanDefinition());
        }
    }
}

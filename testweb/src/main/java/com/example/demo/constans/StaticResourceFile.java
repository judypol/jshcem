package com.example.demo.constans;

import com.shcem.webcore.freemarker.FreemarkerTemplateModel;
import com.shcem.webcore.freemarker.IStaticResource;

/**
 * Created by judysen on 2017/9/9.
 */
public class StaticResourceFile implements IStaticResource {
    //    public static String Prefix="http://www.shcem.com";
//    public static String Version="2014";
//    public static String Bootstrip=Prefix+"http://bootstrip.com"+Version;
    String prefix = "http://www.shcem.com";
    String version = "2014";
    String bootstrip = prefix + "http://bootstrip.com" + version;
    FreemarkerTemplateModel systemConfig=new FreemarkerTemplateModel(new SystemConfig());

    public FreemarkerTemplateModel getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(FreemarkerTemplateModel systemConfig) {
        this.systemConfig = systemConfig;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBootstrip() {
        return bootstrip;
    }

    public void setBootstrip(String bootstrip) {
        this.bootstrip = bootstrip;
    }

}

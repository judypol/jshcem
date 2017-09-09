package com.example.demo.constans;

import com.shcem.webcore.freemarker.IStaticResource;

/**
 * Created by judysen on 2017/9/9.
 */
public class StaticResourceFile implements IStaticResource {
    public static String Prefix="http://www.shcem.com";
    public static String Version="2014";
    public static String Bootstrip=Prefix+"http://bootstrip.com"+Version;
}

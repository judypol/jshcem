package com.shcem.annotation;

import java.lang.annotation.*;

/**
 * Created by judysen on 2017/4/4.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributionLock {

}

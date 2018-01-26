package com.shcem.webcore.permission;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permissions {
    Permission[] value();
}

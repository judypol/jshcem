/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/7/10 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.pool;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author lizhihua
 * @version 1.0
 */
public class BeanPoolFactory<V> extends BaseKeyedPooledObjectFactory<String,V> {
    private Class<V> cls;
    public BeanPoolFactory(Class<V> cls){
        this.cls = cls;
    }
    public V create(String k) throws Exception {
        return this.cls.newInstance();
    }

    public PooledObject<V> wrap(V v) {
        return new DefaultPooledObject<V>(v);
    }
}

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

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;


/**
 * @author lizhihua
 * @version 1.0
 */
public class GenericBeanPool<V> {
    private Class<V> cls;
    private GenericKeyedObjectPool<String,V> pool;
    private String key;

    public GenericBeanPool(Class<V> cls){
        this.cls=cls;
        this.pool=new GenericKeyedObjectPool<String, V>(new BeanPoolFactory<V>(this.cls));
        getKey();
    }
    public GenericBeanPool(Class<V> cls,GenericKeyedObjectPoolConfig config){
        this.cls=cls;
        this.pool=new GenericKeyedObjectPool<String, V>(new BeanPoolFactory<V>(this.cls),config);
        getKey();
    }
    private String getKey(){
        if(this.cls!=null){
            this.key=this.cls.getName();
        }
        return this.key;
    }

    /**
     * 获取pool中的值
     * @return
     * @throws Exception
     */
    public V get() throws Exception{
        return this.pool.borrowObject(this.key);
    }

    /**
     * 等待时间
     * @param borrowMaxWaitMillis
     * @return
     * @throws Exception
     */
    public V get(long borrowMaxWaitMillis) throws Exception {
        return this.pool.borrowObject(key, borrowMaxWaitMillis);
    }

    /**
     *
     */
    public void clear() {
        this.pool.clear(this.key);
    }
    public void close(V v){
        this.pool.returnObject(this.key,v);
    }
}

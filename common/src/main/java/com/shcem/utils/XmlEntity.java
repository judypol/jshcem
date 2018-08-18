package com.shcem.utils;

/**
 * Created by Administrator on 2017/11/26 0026.
 */
/**
 *
 */
public class XmlEntity{
    Object obj;
    /**
     * 修改时间文件
     */
    long modifyDate;

    String md5;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}

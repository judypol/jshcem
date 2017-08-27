package com.shcem.utils.excel;

import java.lang.reflect.Field;

/**
 * Created by lizhihua on 2017/4/12.
 */
public class ExcelHeader {
    String Title;
    Field Field;
    int ColumnIndex;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public java.lang.reflect.Field getField() {
        return Field;
    }

    public void setField(java.lang.reflect.Field field) {
        Field = field;
    }

    public int getColumnIndex() {
        return ColumnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        ColumnIndex = columnIndex;
    }
}

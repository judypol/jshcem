/* ========================================				
 * System Name　　：化交线上平台				
 * SubSystem Name ：化交站点核心工具集
 * File Name: RelationEnum
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.webcore.permission;

/**
 * 
 * @author lizhihua
 * @version 1.0
 */
public enum RelationEnum {
    And(1),
    Or(0);

    private int index;
    private RelationEnum(int index){
        this.index=index;
    }

    @Override
    public String toString() {
        return String.valueOf(this.index);
    }
}

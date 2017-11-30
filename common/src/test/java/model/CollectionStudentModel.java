/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/11/28 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhihua
 * @version 1.0
 */
@XmlRootElement(name="StudentEntity")
public class CollectionStudentModel {
    @XmlElement(name="student")
    List<StudentModel> studentModels;
    public CollectionStudentModel(){
        studentModels=new ArrayList<>();
    }
    public List<StudentModel> getModels() {
        return studentModels;
    }

    public void setModels(List<StudentModel> studentModels) {
        this.studentModels = studentModels;
    }
}

/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/31 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */

import com.alibaba.fastjson.JSON;
import com.shcem.mapper.BeanMapper;
import model.StudentEntity;
import model.StudentModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lizhihua
 * @version 1.0
 */
public class BeanMapperTest {
    @Test
    public void beanMapper(){
        StudentModel model=new StudentModel();
        model.setAge(25);
        model.setBirthday(new Date());
        model.setGrade("1班");
        model.setId("ydswe12345");
        model.setName("李四");

        StudentEntity entity=BeanMapper.map(model, StudentEntity.class);

        System.out.println(JSON.toJSONStringWithDateFormat(entity,"yyyy-MM-dd HH:mm:ss SSS"));
    }
    @Test
    public void beanMapperList(){
        List<StudentModel> models=setData();

        List<StudentEntity> entities=BeanMapper.mapList(models,StudentEntity.class);

        System.out.println(JSON.toJSONStringWithDateFormat(entities,"yyyy-MM-dd HH:mm:ss"));
    }
    private List<StudentModel> setData(){
        List<StudentModel> list=new ArrayList<>();
        StudentModel model=new StudentModel();
        model.setAge(25);
        model.setBirthday(new Date());
        model.setGrade("1班");
        model.setId("ydswe12345");
        model.setName("李四");
        list.add(model);

        StudentModel model1=new StudentModel();
        model1.setAge(26);
        model1.setBirthday(new Date());
        model1.setGrade("2班");
        model1.setId("ydswe12345");
        model1.setName("李四");

        list.add(model1);
        return list;
    }
}

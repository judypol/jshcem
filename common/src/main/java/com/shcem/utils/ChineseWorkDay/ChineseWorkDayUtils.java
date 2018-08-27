/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/4/24 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.utils.ChineseWorkDay;

import com.alibaba.fastjson.JSON;
import com.shcem.common.YamlConfiguration;
import com.shcem.constants.SystemDefine;
import com.shcem.utils.DateUtils;
import com.shcem.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lizhihua
 * @version 1.0
 */
public class ChineseWorkDayUtils {
    private static ChineseWorkDayUtils chineseWorkDayUtils=null;
    public synchronized static ChineseWorkDayUtils Instance() throws Exception{
        if(chineseWorkDayUtils==null){
            String file= YamlConfiguration.instance().getString(SystemDefine.WORKDAYFILE);
            if(StringUtils.isEmpty(file)){
                throw new Exception("请在App.yaml中设置workdayfile");
            }
            try{
                chineseWorkDayUtils=new ChineseWorkDayUtils();
                chineseWorkDayUtils.GetConfigFile(file);

            }catch (Exception ex){
                throw new Exception(ex);
            }
        }
        return chineseWorkDayUtils;
    }
    String fileContext=null;
    Workday workday=null;
    private ChineseWorkDayUtils(){

    }
    /**
     * 获取配置文件Json文件的内容
     * @param filename
     * @return
     * @throws Exception
     */
    private void GetConfigFile(String filename) throws Exception{
        File file=new File(filename);
        if(!file.exists())
            throw new Exception("找不到指定的日期定义文件："+filename);
        fileContext= FileUtils.readFileToString(file,"UTF-8");
        workday=JSON.parseObject(fileContext,Workday.class);
    }

    /**
     * 判断是否是中国式工作日
     * @param date
     * @return
     */
    public boolean IsChineseWorkDay(Date date){
        if(StringUtils.isEmpty(fileContext))
            return  false;

        //Workday workday= JSON.parseObject(fileContext,Workday.class);
        boolean flag=false;
        if(!IsWorkDay(date)){
            flag= workday.Workdays.contains(DateUtils.formatDate(date,"yyyy-MM-dd"));
        }else {
            flag=!workday.Weekends.contains(DateUtils.formatDate(date,"yyyy-MM-dd"));
        }
        return flag;
    }

    /**
     *
     * @param currentDay
     * @param next
     * @return
     */
    public Date nextChineseWorkDay(Date currentDay,int next) throws Exception{
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(currentDay);
        int step=1;
        if(next<0){
            step=-1;
            next=-next;
        }
        int index=0;
        int tempNext=0;
        while(tempNext<next){
            if(index>200+next){
                throw new Exception("workjobday configuration is wrong");
            }
            calendar.add(calendar.DATE,step);
            Date date=calendar.getTime();
            if(IsChineseWorkDay(date)){
                tempNext++;
            }

            index++;
        }
        return calendar.getTime();
    }

    /**
     *
     * @param date
     * @return
     */
    private boolean IsWorkDay(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int d=calendar.get(Calendar.DAY_OF_WEEK);
        if(d==1||d==7){
            return false;
        }else {
            return true;
        }
    }
}

/* ========================================
 * System Name　　：    化交线上平台
 * SubSystem Name ：化交服务核心工具集
 * File Name: LogManagerImpl
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.mybatis.plugin;

import com.shcem.mybatis.query.Pageable;
import com.shcem.utils.Reflections;
import com.shcem.utils.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.PropertyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

//import core.base.query.PageInfo;

/**
 * @author zengxiaoning
 * @version 1.0
 *          Mybatis分页插件实现
 *          最新的jar，中StatementHandler的prepare方法增加了一个Integer transactionTimeout
 */
@SuppressWarnings("unchecked")
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagePlugin implements Interceptor {
    public final static Logger logger = LoggerFactory.getLogger(PagePlugin.class);
    private String dialect;//数据库类型
    //private static String pageSqlId = ""; // mybaits的数据库xml映射文件中需要拦截的ID(正则匹配)

    @SuppressWarnings("rawtypes")
    public Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            //Page<?> page=new Page<>();

            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk
                    .getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) Reflections.
                    GetFieldValue(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) Reflections
                    .GetFieldValue(delegate, "mappedStatement");
            /**
             *
             * 方法1：传入的参数是否有page参数，如果有，则分页，
             */
            BoundSql boundSql = delegate.getBoundSql();
            Object parameterObject = boundSql.getParameterObject();// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
            if (parameterObject == null) {
                return ivk.proceed();
            } else {
                Pageable pageView = null;
                if (parameterObject instanceof Map) {
                    for (Entry entry : (Set<Entry>) ((Map) parameterObject).entrySet()) {
                        if (entry.getValue() instanceof Pageable) {
                            pageView = ((Pageable) entry.getValue());
                            break;
                        }
                    }
                } else if(parameterObject instanceof Pageable){ // 参数为某个实体，该实体拥有Pages属性
                    pageView = (Pageable)parameterObject;
                }

                if (pageView == null) {
                    return ivk.proceed();
                }
                if (pageView.getPageSize() == null || pageView.getPageSize() <= 0) {
                    return ivk.proceed();
                }
                String sql = boundSql.getSql();
                Connection connection = (Connection) ivk.getArgs()[0];

                setPageParameter(sql, connection, mappedStatement, boundSql, parameterObject, pageView); //设置分页总数
                String pageSql = MybatisSqlHelper.generatePagesSql(sql, pageView,dialect); //回调返回不同数据库对应的分页方式
                Reflections.SetFieldValue(boundSql, "sql", pageSql); // 将分页sql语句反射回BoundSql.
            }
            try{
                Object resultObj=ivk.proceed();
                return resultObj;
            }catch (Exception ex){
                throw ex;
            }
        }else{
            return ivk.proceed();
        }
    }

    /**
     * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用者就可用通过 分页参数
     * <code>PageParameter</code>获得相关信息。
     *
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @param pageView
     * @throws SQLException
     */
    @SuppressWarnings("resource")
    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,
                                  BoundSql boundSql, Object parameterObject, Pageable pageView) throws SQLException {
        // lahand 追加 解决 mapper 文件 赋值是 #{XX} 时，出错的问题
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        // 记录总记录数
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            String countSql = "";
            try {
                countSql = "select count(1) from " +MybatisSqlHelper.suffixStr(MybatisSqlHelper.removeOrderBys(sql));
                countStmt = connection.prepareStatement(countSql);
                parameterHandler.setParameters(countStmt);
                rs = countStmt.executeQuery();
            } catch (Exception e) {
                countSql = "select count(1) from (" + sql + ") tmp_count";
                countStmt = connection.prepareStatement(countSql);
                parameterHandler.setParameters(countStmt);
                rs = countStmt.executeQuery();
            }
            int count = 0;
            if (rs.next()) {
                count = ((Number) rs.getObject(1)).intValue();
            }
            pageView.setTotalRecords(count); //设置分页实体的总记录数量
            int i=count%pageView.getPageSize();
            int pages=count/pageView.getPageSize();
            if(i!=0){
                pages+=1;
            }
            pageView.setTotalPages(pages); //设置总页数
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                countStmt.close();
            } catch (Exception e) {
            }
        }
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        if (StringUtils.isEmpty(dialect)) {
            try {
                throw new PropertyException("dialectName or dialect property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
//        pageSqlId = p.getProperty("pageSqlId");//根据id来区分是否需要分页
//        if (isEmpty(pageSqlId)) {
//            try {
//                throw new PropertyException("pageSqlId property is not found!");
//            } catch (PropertyException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
    /**
     * 判断变量是否为空
     *
     * @param s
     * @return
     */
//    public boolean isEmpty(String s) {
//        if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}

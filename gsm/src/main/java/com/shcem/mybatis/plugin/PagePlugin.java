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

import com.shcem.mybatis.query.Page;
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
import java.util.List;
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
    private static String dialect = null;//数据库类型
    //private static String pageSqlId = ""; // mybaits的数据库xml映射文件中需要拦截的ID(正则匹配)

    @SuppressWarnings("rawtypes")
    public Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            Page<?> page=new Page<>();

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

                page.setPageIndex(pageView.getPageIndex());
                page.setPageSize(pageView.getPageSize());
                setPageParameter(sql, connection, mappedStatement, boundSql, parameterObject, page); //设置分页总数
                String pageSql = generatePagesSql(sql, pageView); //回调返回不同数据库对应的分页方式
                Reflections.SetFieldValue(boundSql, "sql", pageSql); // 将分页sql语句反射回BoundSql.
            }
            try{
                Object resultObj=ivk.proceed();
                if(resultObj instanceof List){
                    page.setContent((List)resultObj);
                }
                return page;
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
                                  BoundSql boundSql, Object parameterObject, Page<?> pageView) throws SQLException {
        // lahand 追加 解决 mapper 文件 赋值是 #{XX} 时，出错的问题
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        // 记录总记录数
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            String countSql = "";
            try {
                countSql = "select count(1) from " + suffixStr(removeOrderBys(sql));
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
            pageView.setTotalPages(count / pageView.getPageSize() + 1); //设置总页数
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

    /**
     * select
     * id,
     * articleNo,
     * sum(ddd) ss,
     * articleName,
     * (SELECT loginName from ly_userinfo u where u.id=userId) loginName,
     * (SELECT userName from ly_userinfo u where u.id=userId) userName,
     * sum(ddd) ss
     * from article
     * 兼容以上子查询
     * //去除sql ..from 前面的字符。考虑 aafrom fromdd 等等情况
     */
    public static String suffixStr(String toSql) {
        toSql = toSql.toUpperCase();
        int sun = toSql.indexOf("FROM");
        String f1 = toSql.substring(sun - 1, sun);
        String f2 = toSql.substring(sun + 4, sun + 5);
        if (f1.trim().isEmpty() && f2.trim().isEmpty()) {//判断第一个from的前后是否为空
            String s1 = toSql.substring(0, sun);
            int s0 = s1.indexOf("(");
            if (s0 > -1) {
                int se1 = s1.indexOf("SELECT");
                if (s0 < se1) {
                    if (se1 > -1) {
                        String ss1 = s1.substring(se1 - 1, se1);
                        String ss2 = s1.substring(se1 + 6, se1 + 7);
                        if (ss1.trim().isEmpty() && ss2.trim().isEmpty()) {//判断第一个from的前后是否为空
                            return suffixStr(toSql.substring(sun + 5));
                        }
                    }
                }
                int se2 = s1.indexOf("(SELECT");
                if (se2 > -1) {
                    String ss2 = s1.substring(se2 + 7, se2 + 8);
                    if (ss2.trim().isEmpty()) {//判断第一个from的前后是否为空
                        return suffixStr(toSql.substring(sun + 5));
                    }
                }
                if (se1 == -1 && se2 == -1) {
                    return toSql.substring(sun + 5);
                } else {
                    toSql = toSql.substring(sun + 5);
                }
            } else {
                toSql = toSql.substring(sun + 5);
            }
        }
        return toSql;
    }

    public static void main(String[] args) {
        String sql = "  select " +
                "	articleNo " +
                " from article left jion aefv where 1=(SELECT userName from ly_userinfo u where u.id=userId) "
                + "and id = sdf   order by as asc";
        sql = removeOrderBys(sql);
        System.out.println(sql);
        System.out.println(suffixStr(sql));
    }

    /**
     * 去除Sql的orderBy。
     *
     * @param toSql
     * @return String
     */
    private static String removeOrderBys(String toSql) {
        toSql = toSql.toUpperCase();
        int sun = toSql.indexOf("ORDER BY");
        if (sun > -1) {
            String f1 = toSql.substring(sun - 1, sun);
            String f2 = toSql.substring(sun + 8, sun + 8);
            if (f1.trim().isEmpty() && f2.trim().isEmpty()) {//判断第一个from的前后是否为空
                String zb = toSql.substring(sun);
                int s0 = zb.indexOf(")");
                if (s0 > -1) {//from之前是否有括号
                    String s1 = toSql.substring(0, sun);
                    String s2 = zb.substring(s0);
                    return removeOrderBys(s1 + s2);
                } else {
                    toSql = toSql.substring(0, sun);
                }
            }
        }
        return toSql;
    }

    /**
     * 根据数据库方言，生成特定的分页sql
     *
     * @param sql
     * @param page
     * @return
     */
    public static String generatePagesSql(String sql, Pageable page) {
        if (page != null) {
            if ("mysql".equals(dialect)) {
                return buildPageSqlForMysql(sql, page).toString();
            } else if ("oracle".equals(dialect)) {
                return buildPageSqlForOracle(sql, page).toString();
            } else if ("SQLServer2008".equals(dialect)) {
                return buildPageSqlForSQLServer2008Dialect(sql, page).toString();
            }
        }
        return sql;
    }

    /**
     * mysql的分页语句
     *
     * @param sql
     * @param page
     * @return String
     */
    public static StringBuilder buildPageSqlForMysql(String sql, Pageable page) {
        StringBuilder pageSql = new StringBuilder();
        String beginrow = String.valueOf((page.getPageIndex() - 1) * page.getPageSize()); //limit 0,10 ;第一个参数为起始位置
        //String beginrow=String.valueOf(page.getPageNo());
        pageSql.append(sql);
        pageSql.append(" limit " + beginrow + "," + page.getPageSize());
        return pageSql;
    }

    /**
     * 参考hibernate的实现完成oracle的分页
     *
     * @param sql
     * @param page
     * @return String
     */
    public static StringBuilder buildPageSqlForOracle(String sql, Pageable page) {
        StringBuilder pageSql = new StringBuilder(100);
        String beginrow = String.valueOf((page.getPageIndex()) * page.getPageSize());
        String endrow = String.valueOf(page.getPageIndex() + 1 * page.getPageSize());

        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
        pageSql.append(sql);
        pageSql.append(" ) temp where rownum <= ").append(endrow);
        pageSql.append(") where row_id > ").append(beginrow);
        return pageSql;
    }

    /**
     * 参考hibernate的实现完成SQLServer2008的分页
     *
     * @param sql
     * @param page
     * @return String
     */
    public static String buildPageSqlForSQLServer2008Dialect(String sql, Pageable page) {
        SQLServer2008Dialect dialect = new SQLServer2008Dialect();
        String sqlbuild = dialect.getLimitString(sql, (page.getPageIndex() - 1) * page.getPageSize(), page.getPageSize());
        return sqlbuild;
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

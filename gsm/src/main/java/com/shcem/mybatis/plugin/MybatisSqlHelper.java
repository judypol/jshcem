/* ========================================
 * System Name��������������ƽ̨
 * SubSystem Name ������վ����Ĺ��߼�
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/28 ��lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.mybatis.plugin;

import com.shcem.mybatis.query.Pageable;

/**
 * @author lizhihua
 * @version 1.0
 */
public class MybatisSqlHelper {
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
     * ���������Ӳ�ѯ
     * //ȥ��sql ..from ǰ����ַ������� aafrom fromdd �ȵ����
     */
    public static String suffixStr(String toSql) {
        toSql = toSql.toUpperCase();
        int sun = toSql.indexOf("FROM");
        String f1 = toSql.substring(sun - 1, sun);
        String f2 = toSql.substring(sun + 4, sun + 5);
        if (f1.trim().isEmpty() && f2.trim().isEmpty()) {//
            String s1 = toSql.substring(0, sun);
            int s0 = s1.indexOf("(");
            if (s0 > -1) {
                int se1 = s1.indexOf("SELECT");
                if (s0 < se1) {
                    if (se1 > -1) {
                        String ss1 = s1.substring(se1 - 1, se1);
                        String ss2 = s1.substring(se1 + 6, se1 + 7);
                        if (ss1.trim().isEmpty() && ss2.trim().isEmpty()) {//
                            return suffixStr(toSql.substring(sun + 5));
                        }
                    }
                }
                int se2 = s1.indexOf("(SELECT");
                if (se2 > -1) {
                    String ss2 = s1.substring(se2 + 7, se2 + 8);
                    if (ss2.trim().isEmpty()) {//
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

    /**
     * ȥ��Sql��orderBy��
     *
     * @param toSql
     * @return String
     */
    public static String removeOrderBys(String toSql) {
        toSql = toSql.toUpperCase();
        int sun = toSql.indexOf("ORDER BY");
        if (sun > -1) {
            String f1 = toSql.substring(sun - 1, sun);
            String f2 = toSql.substring(sun + 8, sun + 8);
            if (f1.trim().isEmpty() && f2.trim().isEmpty()) {//
                String zb = toSql.substring(sun);
                int s0 = zb.indexOf(")");
                if (s0 > -1) {//from֮ǰ�Ƿ�������
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
     *
     * @param sql
     * @param page
     * @return
     */
    public static String generatePagesSql(String sql, Pageable page,String dialect) {
        if (page != null) {
            if ("mysql".equals(dialect)) {
                return buildPageSqlForMysql(sql, page).toString();
            } else if ("oracle".equals(dialect)) {
                return buildPageSqlForOracle(sql, page).toString();
            } else if ("mssql".equals(dialect)) {
                return buildPageSqlForSQLServer2008Dialect(sql, page).toString();
            }
        }
        return sql;
    }

    /**
     * mysql
     *
     * @param sql
     * @param page
     * @return String
     */
    public static StringBuilder buildPageSqlForMysql(String sql, Pageable page) {
        StringBuilder pageSql = new StringBuilder();
        String beginrow = String.valueOf((page.getPageIndex() - 1) * page.getPageSize()); //limit 0,10 ;��һ������Ϊ��ʼλ��
        //String beginrow=String.valueOf(page.getPageNo());
        pageSql.append(sql);
        pageSql.append(" limit " + beginrow + "," + page.getPageSize());
        return pageSql;
    }

    /**
     *
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
     *
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
}

/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/11/27 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 货币格式化
 * @author lizhihua
 * @version 1.0
 */
public class CurrencyFormat {
    /**
     * 保留2位小数，并且每3位加，
     * @param money
     * @return
     */
    public static String MoneyRule(BigDecimal money) {
        DecimalFormat d1 = new DecimalFormat("#,##0.00");
        return d1.format(money);
    }

    /**
     *
     * @param quantity
     * @return
     */
    public static String QuantityRule(BigDecimal quantity) {
        DecimalFormat d1 = new DecimalFormat("#,##0");
        return d1.format(quantity);
    }

    /**
     *
     * @param money
     * @return
     */
    public static BigDecimal MoneySaveToDb(BigDecimal money) {
        return money.setScale(2);
    }
}

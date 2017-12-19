/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/18 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package unitTest.beanMapper;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lizhihua
 * @version 1.0
 */
public class Bean1 {
    public String f1;
    int f2;
    Integer f3;
    Date f4;
    BigDecimal f5;
    boolean isChecked;
    byte[] pis;

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public int getF2() {
        return f2;
    }

    public void setF2(int f2) {
        this.f2 = f2;
    }

    public Integer getF3() {
        return f3;
    }

    public void setF3(Integer f3) {
        this.f3 = f3;
    }

    public Date getF4() {
        return f4;
    }

    public void setF4(Date f4) {
        this.f4 = f4;
    }

    public BigDecimal getF5() {
        return f5;
    }

    public void setF5(BigDecimal f5) {
        this.f5 = f5;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public byte[] getPis() {
        return pis;
    }

    public void setPis(byte[] pis) {
        this.pis = pis;
    }
}

/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/1 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package unitTest.beanValidate;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;


/**
 * @author lizhihua
 * @version 1.0
 */
public class Product {
    @NotNull(message = "英文品名不能为空")
    private String CustomsName;
    @NotBlank(message = "中文名不能空（）")
    private String CustomsCnName;
//    @DecimalMin(value = "0", message = "申报价值不能小于0")
    private String DeclareValue;
//    @Pattern(regexp = "\\d", message = "数量不合法") // 正则验证
    private String Quantity;

    public String getCustomsName() {
        return CustomsName;
    }

    public void setCustomsName(String customsName) {
        CustomsName = customsName;
    }

    public String getCustomsCnName() {
        return CustomsCnName;
    }

    public void setCustomsCnName(String customsCnName) {
        CustomsCnName = customsCnName;
    }

    public String getDeclareValue() {
        return DeclareValue;
    }

    public void setDeclareValue(String declareValue) {
        DeclareValue = declareValue;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

}

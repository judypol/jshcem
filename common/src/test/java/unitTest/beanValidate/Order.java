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

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author lizhihua
 * @version 1.0
 */
public class Order {
    @NotNull(message = "订单号不能为空 ")
    //@Size(max = 11, message = "订单号长度不能超过11")
    @Length(max=11,message = "订单号长度不能超过11位")
    private String PlatformOrderID;
    @Valid // 级联验证
    private Receiver Receiver;
    @Valid // 级联验证
    private List<Product> ProductList;

    public String getPlatformOrderID() {
        return PlatformOrderID;
    }

    public void setPlatformOrderID(String platformOrderID) {
        PlatformOrderID = platformOrderID;
    }

    public Receiver getReceiver() {
        return Receiver;
    }

    public void setReceiver(Receiver receiver) {
        Receiver = receiver;
    }

    public List<Product> getProductList() {
        return ProductList;
    }

    public void setProductList(List<Product> productList) {
        ProductList = productList;
    }

}

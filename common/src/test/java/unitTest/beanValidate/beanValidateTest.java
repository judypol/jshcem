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

import com.shcem.utils.BeanValidatorUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhihua
 * @version 1.0
 */
public class beanValidateTest {
    @Test
    public void beanValidatorTest(){
        Order order = new Order();
        order.setPlatformOrderID("111111111111111"); // 不合法的订单长度

        Receiver receiver = new Receiver();
        receiver.setEmail("dddd"); // 不合法的邮箱
        order.setReceiver(receiver);

        List<Product> products = new ArrayList<Product>();
        Product product = new Product();
        product.setQuantity("1.0"); // 不合法的数量
        product.setDeclareValue("-1"); // 不合法的申报价值
        product.setCustomsCnName("");
        products.add(product);
        order.setProductList(products);

        String validatorString= BeanValidatorUtils.beanValidator(order);
        System.out.println(validatorString);
    }

}

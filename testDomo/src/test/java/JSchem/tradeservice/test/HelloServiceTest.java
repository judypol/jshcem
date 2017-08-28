/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/22 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package JSchem.tradeservice.test;

import JSchem.tradeservice.test.serviceContract.IHelloService;
import com.caucho.hessian.client.HessianProxyFactory;
import org.junit.Test;

/**
 * @author lizhihua
 * @version 1.0
 */
public class HelloServiceTest {
    public void before(){

    }
    @Test
    public void HelloServiceHelloTest() throws Exception{
        HessianProxyFactory factory = new HessianProxyFactory();
        IHelloService exampleInterface = (IHelloService) factory.create(IHelloService.class, "http://localhost:8080/tradeservice/IHelloService");
        String hello =exampleInterface.sayHello();

        System.out.println(hello);
    }
    @Test
    public void HelloServiceListTest() throws Exception{
        HessianProxyFactory factory = new HessianProxyFactory();
        IHelloService exampleInterface = (IHelloService) factory.create(IHelloService.class, "http://localhost:8080/tradeservice/IHelloService");
        String hello =exampleInterface.sayAllHello();

        System.out.println(hello);
    }
}
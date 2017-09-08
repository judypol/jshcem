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
import org.junit.rules.Stopwatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    @Test
    public void HelloSyncTest() throws Exception{
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(1000);
        long startTime=System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            executor.execute(new HelloSyncable(latch,i));
        }
        long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);
        //Thread.sleep(5000);
    }

    class HelloSyncable implements Runnable{
        int count;
        CountDownLatch latch;
        public HelloSyncable(CountDownLatch latch,int i){
            //System.out.println("Count:"+i);
            count=i;
            this.latch=latch;
        }
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            HessianProxyFactory factory = new HessianProxyFactory();
            try{
                IHelloService exampleInterface = (IHelloService) factory.create(IHelloService.class, "http://localhost:8080/tradeservice/IHelloService");
                String hello =exampleInterface.sayAllHello();

                //System.out.println("Count: "+count+",content:"+hello);
            }catch (Exception ex){
                System.out.println(ex);
            }finally {
                this.latch.countDown();
            }
        }
    }
}
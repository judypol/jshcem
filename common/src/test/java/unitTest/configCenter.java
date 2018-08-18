/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/5 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package unitTest;

import com.shcem.common.YamlConfiguration;
import com.shcem.configCenter.spring.AutoConfigCenter;
import com.shcem.configCenter.spring.ConfigCenterRegister;
import org.junit.Test;

/**
 * @author lizhihua
 * @version 1.0
 */
@AutoConfigCenter
public class configCenter {
    @Test
    public void configCenterTest() throws Exception{
        ConfigCenterRegister ccr=new ConfigCenterRegister();
        ccr.init();
        Thread thread=new Thread(new runSpring());
        thread.start();

        Thread.sleep(80000);

        System.out.println(YamlConfiguration.instance().getString("redis.model"));
        System.out.println(YamlConfiguration.instance().getString("SystemConfig"));
    }
    public class runSpring implements Runnable{
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
//            SpringPropertyInjectSupport springPropertyInjectSupport=new SpringPropertyInjectSupport();
//            springPropertyInjectSupport.setConfigNameSpaces("cirpc/dnet/dev");
//            springPropertyInjectSupport.init();
            //ConfigCenterRegister register=new ConfigCenterRegister();
            //register.init();

            System.out.println(YamlConfiguration.instance().getString("redis.model"));
            System.out.println(YamlConfiguration.instance().getString("SystemConfig"));
        }
    }
}

/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/10/30 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */

import com.shcem.common.MidTierRequest;
import com.shcem.common.RequestData;
import com.shcem.common.ResponseData;
import org.junit.Test;

/**
 * @author lizhihua
 * @version 1.0
 */
public class MidTierTest {
    @Test
    public void MidTierRequestTest(){
        RequestData data=new RequestData();
        data.setParams("");
        data.setMethodName("GetCategoryListForQuery");
        data.setServiceName("Shcem.Trade.ServiceContract.ICategoryBrandService");

        ResponseData responseData=MidTierRequest.Post(data);
        System.out.println(responseData);
    }
    @Test
    public void MultiMidTierRequest(){
        for (int i=0;i<100;i++){
            MidTierRequestTest();
        }
    }
}

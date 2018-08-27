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

import com.shcem.common.HttpUtlis;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lizhihua
 * @version 1.0
 */
public class HttpUtilTest {
    @Test
    public void getTest() throws Exception{
        String response = HttpUtlis.Instance().get("http://baidu.com");

        System.out.println(response);
    }
    @Test
    public void postFormByEncode() throws Exception{
        String url="http://vip.mston.com/SMSAPI/SMSSV.aspx";
        Map<String,String> map=new HashMap<>();
        map.put("Command","SendSMS");
        map.put("Username","shymcbi11");
        map.put("Password","shymcbi12");
        map.put("Mobiles","13818894125");
        map.put("Msg","测试类容");
        map.put("AtTime","");
        map.put("ExNo","");

        String response=HttpUtlis.Instance().postByForm(url,map,null);

        System.out.println(response);
    }
}

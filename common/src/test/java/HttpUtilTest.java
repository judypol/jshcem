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
}

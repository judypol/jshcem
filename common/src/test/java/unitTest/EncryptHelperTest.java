/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/12/6 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package unitTest;

import com.shcem.Encrypt.TripleDESHelper;
import org.junit.Test;

/**
 * @author lizhihua
 * @version 1.0
 */
public class EncryptHelperTest {
    @Test
    public void thirdEncryptHelper() throws Exception{
        String des=TripleDESHelper.Instance().encryptString("111111","O6WoddOCIQpFDkitnwDriRysU5Vltmxm");

        String src=TripleDESHelper.Instance().decryptString(des,"ShhjPwdKey");
        System.out.println(src);
    }
}

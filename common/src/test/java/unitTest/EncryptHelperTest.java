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

import com.shcem.Encrypt.AESHelper;
import com.shcem.Encrypt.TripleDESHelper;
import com.shcem.utils.RandomCode;
import org.apache.commons.codec.binary.Base64;
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
    @Test
    public void generate3DesKey() throws Exception{
        byte[] ss=RandomCode.Instance().RandomCoder(24).getBytes("UTF-8");
        String bs= Base64.encodeBase64String(ss);

        System.out.println(bs.length());
    }
    @Test
    public void generateAES() throws Exception{
        String key=")O[NB]6,YF}+efcaj{+oESb9d8>Z'e9M";
        String encryptString=AESHelper.Instance().encryptString("13818894125",key);
        System.out.println(encryptString);

        String plainString=AESHelper.Instance().decryptString(encryptString,key);
        System.out.println(plainString);
    }
}

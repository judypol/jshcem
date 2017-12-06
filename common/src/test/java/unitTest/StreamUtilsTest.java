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

import com.shcem.utils.StreamUtils;
import org.junit.Test;

import java.io.*;

/**
 * @author lizhihua
 * @version 1.0
 */
public class StreamUtilsTest {
    @Test
    public void input2Output() throws Exception{
        FileInputStream inputStream=new FileInputStream(new File("生鲜港平台架构(V1.0)文件服务.pptx"));
        OutputStream outputStream= StreamUtils.inputStream2OutputStream(inputStream);
    }
    @Test
    public void output2input() throws Exception{
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        String writeTest="您好！";
        outputStream.write(writeTest.getBytes("utf-8"));
        InputStream inputStream=StreamUtils.outputStream2InputStream(outputStream);
    }
}

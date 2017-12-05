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
package unitTest;

import com.shcem.utils.GzipUtils;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author lizhihua
 * @version 1.0
 */
public class GzipUtilsTest {
    @Test
    public void gzipUtils() throws Exception{
        String source="大是大非的人，的意思服务";
        String gzipCompress= GzipUtils.compress2String(source);

        String uncompressString=GzipUtils.uncompress2String(gzipCompress);

        Assert.isTrue(source.equals(uncompressString));
    }
}

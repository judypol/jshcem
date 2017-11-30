/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/11/28 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package unitTest;

import com.shcem.utils.ConvertUtils;
import org.junit.Test;

/**
 * @author lizhihua
 * @version 1.0
 */
public class ConvertUtilsTest {
    @Test
    public void convert2Int(){
        Integer integer= (Integer) ConvertUtils.convert("2",Integer.class);
    }
    @Test
    public void convertTTest(){
        Integer i=ConvertUtils.convert(2.00,Integer.class);

        Double d=ConvertUtils.convert(2,Double.class);
    }
}

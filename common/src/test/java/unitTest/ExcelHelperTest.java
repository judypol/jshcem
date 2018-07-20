package unitTest;

import com.alibaba.fastjson.JSON;
import com.shcem.utils.excel.ExcelHelper;
import model.ExcelModel;
import org.junit.Test;

import java.util.List;

public class ExcelHelperTest {
    @Test
    public void Excel2List() throws Exception{
        String fileName="F:\\tmp\\银行认账流水模板（GH）0706002 - 副本(1).xlsx";
        List<ExcelModel> ll= ExcelHelper.Instance().Excel2List(fileName,"Sheet1",ExcelModel.class,1);
        System.out.println(JSON.toJSONString(ll));
    }
}

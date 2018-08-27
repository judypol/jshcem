package JSchem.tradeservice.test.pdfTest;

import com.shcem.common.pdf.PdfUtils;
import org.junit.Test;

/**
 * Created by lizhihua on 2018/8/27.
 */
public class pdfTest {
    @Test
    public void pdf2html() throws Exception{
        PdfUtils.html2pdf("demo1.pdf","<h1 style='color:red;'>this is PDF文档！</h1");
    }
}

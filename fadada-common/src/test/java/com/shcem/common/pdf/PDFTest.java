package com.shcem.common.pdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PDFTest {
    @Test
    public void pdf2html() throws Exception{
        File file=new File("demo1.pdf");
        PdfUtils.html2pdf(file,"<h1>这是一个测试pdf2html</h1>");
    }
    @Test
    public void manipulatePdf() throws Exception {
        String src="F:\\gitlab\\mltp\\meg电子合同2-24_修改后.pdf";
        String dest="demo2.pdf";
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        int pages=pdfDoc.getNumberOfPages();
        PdfAcroForm form=PdfAcroForm.getAcroForm(pdfDoc,true);

        String inputStream = getClass().getResource("/simsun.ttc").getFile();
        PdfFont sysFont = PdfFontFactory.createFont(inputStream+",1", PdfEncodings.IDENTITY_H, false);

        form.getField("totalPrice").setValue("中文字体", sysFont,11);

        pdfDoc.close();
    }
    @Test
    public void templates2Pdf() throws Exception{
        String src="F:\\gitlab\\mltp\\meg电子合同2-24_修改后.pdf";
        String dest="demo2.pdf";
        Map<String,String> val=new HashMap<>();
        val.put("totalPrice","中文测试文档");
        val.put("buyerFirmName","我是Buyer方公司名");
        PdfUtils.templates2Pdf(src,dest,val);
    }
}

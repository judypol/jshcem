package com.shcem.common.pdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.font.FontProvider;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

public class PdfUtils {
    /**
     * 将html写入pdf文件
     * @param file
     * @param htmlStr
     * @throws Exception
     */
    public static void html2pdf(File file,String htmlStr) throws Exception{
        String html=getHtmlString(htmlStr);

        HtmlConverter.convertToPdf(html,new PdfWriter(file),getConverterProperties());

    }
    private static String getHtmlString(String htmlStr) throws Exception{
        if(htmlStr.startsWith("http://")||htmlStr.startsWith("https://")){
            return HttpUtils.get(htmlStr);
        }else{
            return htmlStr;
        }
    }
    private static ConverterProperties getConverterProperties(){
        ConverterProperties properties = new ConverterProperties();
        FontProvider font = new FontProvider();
        //font.addSystemFonts();
        String inputStream = PdfUtils.class.getResource("/simsun.ttc").getFile();
        font.addFont(inputStream+",1");     //宋体
        properties.setFontProvider(font);
        return properties;
    }
    /**
     * 将html写入pdf文件
     * @param outputStream
     * @param htmlStr
     * @throws Exception
     */
    public static void html2pdf(OutputStream outputStream,String htmlStr) throws Exception{
        String html=getHtmlString(htmlStr);
        HtmlConverter.convertToPdf(html,new PdfWriter(outputStream),getConverterProperties());
    }

    /**
     * 将html写入pdf文件
     * @param fileName
     * @param htmlStr
     * @throws Exception
     */
    public static void html2pdf(String fileName,String  htmlStr) throws Exception{
        String html=getHtmlString(htmlStr);
        HtmlConverter.convertToPdf(html,new PdfWriter(fileName),getConverterProperties());
    }

    /**
     * 通过模板生成目标pdf文件
     * @param templateFile 模板文件
     * @param fileName 目标文件
     * @throws Exception
     */
    public static void templates2Pdf(String templateFile,String fileName,Map<String,String> val) throws Exception{
        templates2Pdf(templateFile,fileName,val,11);
    }

    /**
     * 通过模板生成目标pdf文件
     * @param templateFile
     * @param fileName
     * @param val
     * @param fontSize
     * @throws Exception
     */
    public static void templates2Pdf(String templateFile,String fileName,Map<String,String> val,int fontSize) throws Exception{
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(templateFile), new PdfWriter(fileName));
        int pages=pdfDoc.getNumberOfPages();
        PdfAcroForm form=PdfAcroForm.getAcroForm(pdfDoc,true);

        String inputStream = PdfUtils.class.getResource("/simsun.ttc").getFile();
        PdfFont sysFont = PdfFontFactory.createFont(inputStream+",1", PdfEncodings.IDENTITY_H, false);
        Map<String, PdfFormField> fields=form.getFormFields();
        for(Map.Entry<String,PdfFormField> entry :fields.entrySet()){
            String key=entry.getKey();
            String fieldKey=entry.getKey();
            if(key.indexOf(".")>0){
                fieldKey=key.substring(0,key.indexOf("."));
            }

            if(val.containsKey(fieldKey)){
                entry.getValue().setValue(val.get(fieldKey),sysFont,fontSize);
            }
        }

        pdfDoc.close();
    }
}

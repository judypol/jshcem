
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class PDFTest {
    @Test
    public void readPDF() throws Exception{
//        //Tika默认是10*1024*1024，这里防止文件过大导致Tika报错
//        BodyContentHandler handler = new BodyContentHandler(100 * 1024 * 1024);
//
//        Metadata metadata = new Metadata();
//        FileInputStream inputstream = new FileInputStream(new File("D:\\tmp\\111.pdf"));
//        ParseContext pcontext = new ParseContext();
//
//        // 解析PDF文档时应由超类AbstractParser的派生类PDFParser实现
//        PDFParser pdfparser = new PDFParser();
//        pdfparser.parse(inputstream, handler, metadata, pcontext);
//
//        // 获取PDF文档的内容
//        System.out.println("PDF文档内容:" + handler.toString());
//
//        // 获取PDF文档的元数据
//        System.out.println("PDF文档元数据:");
//        String[] metadataNames = metadata.names();
//
//        for (String name : metadataNames) {
//            System.out.println(name + " : " + metadata.get(name));
//        }
    }
}

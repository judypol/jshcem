package unitTest;

import com.alibaba.fastjson.JSON;
import com.shcem.mapper.JaxbMapper;
import com.shcem.utils.FileUtils;
import com.shcem.utils.StringUtils;
import com.shcem.utils.XmlUtils;
import model.CollectionStudentModel;
import model.StudentEntity;
import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/11/25 0025.
 */
public class XmlUnitTest {
    @Test
    public void xml2Entity() throws Exception{
        File file=new File("D:\\JavaWorkspace\\github\\jshcem\\common\\src\\test\\resources\\students.xml");
        String xmlString=FileUtils.readFileToString(file);
        xmlString= StringUtils.replace(xmlString,"\r\n","");
        StudentEntity studentEntity=JaxbMapper.fromXml(xmlString,StudentEntity.class);
    }
    @Test
    public void xml2EntityUtils() throws Exception{
        String filename="classpath:students.xml";
        CollectionStudentModel studentEntity= XmlUtils.xml2Entity(filename,CollectionStudentModel.class);

        System.out.print(JSON.toJSONString(studentEntity));
        //Thread.sleep(50000);

        //studentEntity=XmlUtils.xml2Entity(filename,StudentEntity.class);
        //System.out.print(JSON.toJSONString(studentEntity));
    }
}

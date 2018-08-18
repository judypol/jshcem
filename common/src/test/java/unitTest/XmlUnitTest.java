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
import org.springframework.util.ResourceUtils;

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
        File file= ResourceUtils.getFile(filename);
        CollectionStudentModel studentEntity= XmlUtils.xml2Entity(file,CollectionStudentModel.class);

        System.out.print(JSON.toJSONString(studentEntity));
        //Thread.sleep(50000);

        //studentEntity=XmlUtils.xml2Entity(filename,StudentEntity.class);
        //System.out.print(JSON.toJSONString(studentEntity));
    }
    @Test
    public void xml2Entity4String() throws Exception{
        String xmlContents="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<StudentEntity>\n" +
                "    <student>\n" +
                "        <id>4</id>\n" +
                "        <name>lisi</name>\n" +
                "        <age>23</age>\n" +
                "        <birthday>1999-01-23</birthday>\n" +
                "        <grade>一年级</grade>\n" +
                "    </student>\n" +
                "    <student>\n" +
                "        <id>5</id>\n" +
                "        <name>lisi</name>\n" +
                "        <age>23</age>\n" +
                "        <birthday>1999-01-23</birthday>\n" +
                "        <grade>一年级</grade>\n" +
                "    </student>\n" +
                "    <student>\n" +
                "        <id>6</id>\n" +
                "        <name>lisi</name>\n" +
                "        <age>23</age>\n" +
                "        <birthday>1999-01-23</birthday>\n" +
                "        <grade>一年级</grade>\n" +
                "    </student>\n" +
                "</StudentEntity>";
        CollectionStudentModel studentModel=XmlUtils.xml2Entity(xmlContents,CollectionStudentModel.class);
        xmlContents="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<StudentEntity>\n" +
                "    <student>\n" +
                "        <id>4</id>\n" +
                "        <name>lisi</name>\n" +
                "        <age>23</age>\n" +
                "        <birthday>1999-01-23</birthday>\n" +
                "        <grade>一年级</grade>\n" +
                "    </student>\n" +
                "    <student>\n" +
                "        <id>5</id>\n" +
                "        <name>lisi</name>\n" +
                "        <age>23</age>\n" +
                "        <birthday>1999-01-23</birthday>\n" +
                "        <grade>一年级</grade>\n" +
                "    </student>\n" +
                "    <student>\n" +
                "        <id>6</id>\n" +
                "        <name>lisi</name>\n" +
                "        <age>23</age>\n" +
                "        <birthday>1999-01-23</birthday>\n" +
                "        <grade>一年级</grade>\n" +
                "    </student>\n" +
                "    <student>\n" +
                "        <id>6</id>\n" +
                "        <name>lisi4</name>\n" +
                "        <age>23</age>\n" +
                "        <birthday>1999-01-24</birthday>\n" +
                "        <grade>一年级</grade>\n" +
                "    </student>\n" +
                "</StudentEntity>";
        CollectionStudentModel studentModel1=XmlUtils.xml2Entity(xmlContents,CollectionStudentModel.class);
        System.out.print(JSON.toJSONString(studentModel));
    }
}

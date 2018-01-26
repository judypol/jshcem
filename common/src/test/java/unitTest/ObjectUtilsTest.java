package unitTest;

import com.shcem.utils.ObjectUtils;
import model.StudentEntity;
import org.junit.Test;

import java.util.Map;

public class ObjectUtilsTest {
    @Test
    public void bean2Map(){
        StudentEntity studentEntity=new StudentEntity();
        studentEntity.setAge(12);

        Map map=ObjectUtils.transBean2Map(studentEntity);
    }
}

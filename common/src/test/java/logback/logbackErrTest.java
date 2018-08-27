package logback;

import com.shcem.logback.EnableLogbackFile;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EnableLogbackFile
public class logbackErrTest {
    @Test
    public void logbackLevelErr(){
        Logger logger= LoggerFactory.getLogger("controller");
        int a=0;
        int b=2;
        try{
            int c=b/a;
        }catch (Exception ex){
            logger.error("错误日志，",ex);
        }
    }
}

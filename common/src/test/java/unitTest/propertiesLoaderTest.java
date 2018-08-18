package unitTest;

import com.shcem.common.PropertiesLoader;
import org.junit.Test;

public class propertiesLoaderTest {
    @Test
    public void loaderFromString(){
        String contents="driverClassName=com.mysql.jdbc.Driver\n" +
                "url=jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8\n" +
                "username=root\n" +
                "password=123456\n" +
                "filters=stat\n" +
                "initialSize=2\n" +
                "maxActive=300\n" +
                "maxWait=60000\n" +
                "timeBetweenEvictionRunsMillis=60000\n" +
                "minEvictableIdleTimeMillis=300000\n" +
                "validationQuery=SELECT 1\n" +
                "testWhileIdle=true\n" +
                "testOnBorrow=false\n" +
                "testOnReturn=false\n" +
                "poolPreparedStatements=false\n" +
                "maxPoolPreparedStatementPerConnectionSize=200";
        PropertiesLoader propertiesLoader=new PropertiesLoader(contents);
        System.out.print(propertiesLoader.getProperty("url"));
    }
}

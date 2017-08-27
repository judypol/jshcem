package hessianService;

import hessianService.Impl.HelloServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by judysen on 2017/8/20.
 */
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class ServiceTest {
    //@Autowired
    static HelloServiceImpl helloService;
    @Autowired
    protected static ApplicationContext ctx;

    @BeforeClass
    public static void init() {//junit之前init spring
        ctx = new ClassPathXmlApplicationContext("classpath:spring.xml");//这里路径之前没有配对于是一直出错
        //projectService = (ProjectService)context.getBean("projectService");
        helloService=ctx.getBean(HelloServiceImpl.class);
    }
    @Test
    public void initService(){
         //helloService=ctx.getBean(HelloServiceImpl.class);
         String say=helloService.sayHello("lisi");
         System.out.println(say);
    }
}

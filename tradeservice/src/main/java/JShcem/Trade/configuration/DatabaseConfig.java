package JShcem.Trade.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.shcem.mybatis.plugin.PagePlugin;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by judysen on 2017/8/20.
 */
@Configuration
public class DatabaseConfig {
    @Bean
    public DruidDataSource druidDataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        //--基本属性--
        dataSource.setUsername("root");
        dataSource.setPassword("2345678");
        dataSource.setUrl("jdbc:mysql://192.168.61.119:3306/test");

        //配置初始化大小、最小、最大
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(300);
        dataSource.setMinIdle(2);

        //配置获取连接等待超时的时间
        dataSource.setMaxWait(60000);
        try{
            dataSource.setFilters("stat");
        }catch (SQLException ex){
            System.out.println(ex);
        }

        return dataSource;
    }
    @Bean()
    public SqlSessionFactory sqlSessionFactoryBean(ApplicationContext context) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource());
        sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath*:mapper/tradeservice/*.xml"));
        try{
            PagePlugin pagePlugin=new PagePlugin();
            pagePlugin.setDialect("mysql");
            Interceptor[] interceptors={pagePlugin};
            //interceptors[0]=pagePlugin();
            sqlSessionFactoryBean.setPlugins(interceptors);
//            Properties properties=new Properties();
//            properties.setProperty("dialect","mysql");
//            sqlSessionFactoryBean.setConfigurationProperties(properties);
            return sqlSessionFactoryBean.getObject();
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    @Bean
    public PagePlugin pagePlugin(){
        return new PagePlugin();
    }
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("JShcem.**.dao");

        return mapperScannerConfigurer;
    }
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(druidDataSource());
        return dataSourceTransactionManager;
    }
}

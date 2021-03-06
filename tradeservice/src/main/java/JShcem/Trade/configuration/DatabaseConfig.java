package JShcem.Trade.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.shcem.mybatis.plugin.PagePlugin;
import com.shcem.mybatis.query.Page;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Created by judysen on 2017/8/20.
 */
@Configuration
@Primary
public class DatabaseConfig {
//    @Bean
//    public DruidDataSource druidDataSource(){
//        DruidDataSource dataSource=new DruidDataSource();
//        //--基本属性--
////        dataSource.setUsername("root");
////        dataSource.setPassword("2345678");
////        dataSource.setUrl("jdbc:mysql://192.168.61.119:3306/test");
//
//        dataSource.setUrl("jdbc:sqlserver://192.168.60.107:1433;databaseName=HJOnline;user=HJOnline;password=123456");
//        //dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//        //配置初始化大小、最小、最大
//        dataSource.setInitialSize(2);
//        dataSource.setMaxActive(300);
//        dataSource.setMinIdle(1);
//
//        //配置获取连接等待超时的时间
//        dataSource.setMaxWait(60000);
//        //dataSource.setFilters("stat");
//
//        return dataSource;
//    }
//    @Autowired
//    DruidDataSource druidDataSource;
//    @Bean()
//    public SqlSessionFactory sqlSessionFactoryBean(ApplicationContext context) throws Exception{
//        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(druidDataSource);
//        sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath*:mapper/**/*.xml"));
//        try{
//            PagePlugin plugin=new PagePlugin();
//            //plugin.setDialect("mysql");
//            plugin.setDialect("mssql");
//            Interceptor[] plugins={plugin};
//            sqlSessionFactoryBean.setPlugins(plugins);
//            return sqlSessionFactoryBean.getObject();
//        }catch (Exception ex){
//            ex.printStackTrace();
//            throw new RuntimeException(ex);
//        }
//    }
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer(){
//        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("**.dao");
//        return mapperScannerConfigurer;
//    }
//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager(){
//        DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(druidDataSource());
//        return dataSourceTransactionManager;
//    }
}

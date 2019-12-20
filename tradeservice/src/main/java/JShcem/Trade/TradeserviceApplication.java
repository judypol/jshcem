package JShcem.Trade;

import com.shcem.aop.EnableCommon;
import com.shcem.database.EnableDatabase;
import com.shcem.hessian.EnableHessianServiceScanner;
import com.shcem.logback.EnableLogbackFile;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("**.dao")
@EnableTransactionManagement
//@EnableCommon
@EnableLogbackFile
@EnableHessianServiceScanner
public class TradeserviceApplication {//extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TradeserviceApplication.class, args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		builder.sources(this.getClass());
//		return super.configure(builder);
//	}
}

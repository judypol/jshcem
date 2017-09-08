package JShcem.Trade;

import com.shcem.common.YamlConfiguration;
import com.shcem.database.EnableDatabase;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("**.dao")
@EnableTransactionManagement
@EnableDatabase(url= "",user="root",password = "1234")
public class TradeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeserviceApplication.class, args);
	}
}

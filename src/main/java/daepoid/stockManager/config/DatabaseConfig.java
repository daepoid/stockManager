package daepoid.stockManager.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
//        dataSourceBuilder.url("jdbc:mysql://localhost:3306/stockManager?useSSL=false&serverTimezone=UTC&autoReconnect=true");
        dataSourceBuilder.url("jdbc:mysql://localhost:3308/stockManager?useSSL=false&serverTimezone=UTC&autoReconnect=true");
//        dataSourceBuilder.url("jdbc:mysql://mysql-container:3306/stockManager?useSSL=false&serverTimezone=UTC&autoReconnect=true");
//        dataSourceBuilder.url("jdbc:mysql://mysql-container:3306/stockManager?useSSL=false&serverTimezone=UTC");
        return dataSourceBuilder.build();
    }
}

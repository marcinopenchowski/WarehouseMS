package com.wms.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.wms")
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class AppConfig {

    private final Environment environment;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        config.setUsername(environment.getProperty("spring.datasource.username"));
        config.setPassword(environment.getProperty("spring.datasource.password"));
        config.addDataSourceProperty("dataSourceClassName",
                "com.microsoft.sqlserver.jdbc.SQLServerDataSource"
        );
        config.addDataSourceProperty("databaseName",
                environment.getProperty("spring.datasource.databaseName")
        );

        return new HikariDataSource(config);
    }
}
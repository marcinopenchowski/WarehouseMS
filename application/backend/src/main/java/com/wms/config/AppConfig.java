package com.wms.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.wms")
@PropertySource("classpath:application.properties")
public class AppConfig {

    private final Environment environment;

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource(){
        var source = new ResourceBundleMessageSource();
        source.setBasename("classpath:message.properties");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }

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
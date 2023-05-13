package com.chess.chess.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

@Configuration
public class MariaDBConfig
{
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource()
    {

        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();

        dataSource.setSuppressClose(true);

        return dataSource;
    }
}

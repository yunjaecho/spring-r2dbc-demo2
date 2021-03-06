package com.yunjae.springr2dbcdemo2.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.function.DatabaseClient;

// 아래의 주석을 풀면 구 방식으로 처리
//@Configuration
public class ConnectionFactoryConfiguration_Old {

    @Value("${pogresql.database.host}")
    private String POSGRESQL_HOST;

    @Value("${pogresql.database.database}")
    private String POSGRESQL_DATABASE;

    @Value("${pogresql.database.username}")
    private String POSGRESQL_USERNAME;

    @Value("${pogresql.database.password}")
    private String POSGRESQL_PASSWORD;

    @Bean
    DatabaseClient databaseClient() {
        return DatabaseClient.create(connectionFactory());
    }

    @Bean
    ConnectionFactory connectionFactory() {
        PostgresqlConnectionConfiguration config = PostgresqlConnectionConfiguration
                .builder()
                .host(POSGRESQL_HOST)
                .database(POSGRESQL_DATABASE)
                .username(POSGRESQL_USERNAME)
                .password(POSGRESQL_PASSWORD)
                .build();
        return new PostgresqlConnectionFactory(config);
    }
}

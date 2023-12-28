package com.test.doofus.configuration;

import com.test.doofus.properties.PostgresProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatasourceConfiguration {

    PostgresProperties postgresProperties;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username(postgresProperties.getUsername())
                .password(postgresProperties.getPassword())
                .url(postgresProperties.getUri())
                .driverClassName(postgresProperties.getDriver())
                .build();
    }
}

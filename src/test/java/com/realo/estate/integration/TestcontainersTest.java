package com.realo.estate.integration;

import com.realo.estate.integration.annotation.IntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@IntegrationTest
@Sql(value = "classpath:sql/data.sql")
@WithMockUser(username = "test123", password = "test", authorities = {"ADMIN", "AGENT", "USER"})
public abstract class TestcontainersTest {

    private static final PostgreSQLContainer<?> container
            = new PostgreSQLContainer<>("postgres:14.1");
    private static final String DATASOURCE_URL_PROPERTY = "spring.datasource.url";

    @BeforeAll
    static void run() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add(DATASOURCE_URL_PROPERTY, container::getJdbcUrl);
    }
}
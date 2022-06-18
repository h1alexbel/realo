package com.realo.estate.config;

import com.realo.estate.RealoApiApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableEnversRepositories(basePackageClasses = RealoApiApplication.class)
@Configuration
public class AuditConfiguration {
}
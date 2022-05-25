package com.realo.estate.config;

import com.realo.estate.RealEstateAppApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableEnversRepositories(basePackageClasses = RealEstateAppApplication.class)
@Configuration
public class AuditConfiguration {
}
package com.forex.patterns.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.forex.patterns.repository")
@EntityScan(basePackages = "com.forex.patterns.model")
public class PersistanceConfig {

}

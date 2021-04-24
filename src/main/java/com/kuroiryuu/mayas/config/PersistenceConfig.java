package com.kuroiryuu.mayas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.kuroiryuu.mayas.dao")
@EnableTransactionManagement
public class PersistenceConfig {
}

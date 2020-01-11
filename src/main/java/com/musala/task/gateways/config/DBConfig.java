package com.musala.task.gateways.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.musala.task.gateways.models.repository")
@EnableTransactionManagement
public class DBConfig {

}

package com.zp.zorritoplus.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "zorritoPlusEntityManagerFactory", transactionManagerRef = "zorritoPlusTransactionManager", basePackages = "com.zp.zorritoplus.repository")
public class MysqlDatasourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.zorrito")
    public DataSourceProperties zorritoPlusDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.zorrito.configuration")
    public DataSource zorritoPlusDatasource() {
        return zorritoPlusDatasourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "zorritoPlusEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean zorritoPlusEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(zorritoPlusDatasource())
                .packages("com.zp.zorritoplus.model.domain").build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager zorritoPlusTransactionManager(
            final @Qualifier("zorritoPlusEntityManagerFactory") LocalContainerEntityManagerFactoryBean zorritoPlusEntityManagerFactory) {
        return new JpaTransactionManager(zorritoPlusEntityManagerFactory.getObject());
    }
}
